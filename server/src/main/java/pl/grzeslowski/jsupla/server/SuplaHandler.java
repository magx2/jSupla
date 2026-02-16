package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jakarta.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicReference;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

final class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuplaHandler.class);
    private final String uuid;
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;
    private final MessageHandler messageHandler;

    private final AtomicReference<SuplaDefaultWriter> writer = new AtomicReference<>();

    SuplaHandler(
            String uuid,
            CallTypeParser callTypeParser,
            DecoderFactory decoderFactory,
            EncoderFactory encoderFactory,
            MessageHandler messageHandler) {
        this.uuid = uuid;
        LOGGER.debug("New instance {}, instanceId={}", getClass().getSimpleName(), this.uuid);
        this.messageHandler = messageHandler;
        this.callTypeParser = callTypeParser;
        this.decoderFactory = decoderFactory;
        this.encoderFactory = encoderFactory;
    }

    @Override
    public void channelActive(@Nonnull ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("SuplaHandler.channelActive(ctx), instanceId={}", uuid);
        super.channelActive(ctx);
        val writer = new SuplaDefaultWriter(uuid, encoderFactory, ctx);
        this.writer.set(writer);
        messageHandler.active(writer);
    }

    @Override
    public void channelInactive(@Nonnull ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("SuplaHandler.channelInactive(ctx), instanceId={}", uuid);
        messageHandler.inactive();
        writer.set(null);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SuplaDataPacket suplaDataPacket) {
        {
            var writer = this.writer.get();
            if (writer != null) {
                writer.setVersion(suplaDataPacket.version());
            }
        }
        val msg = "SuplaHandler.channelRead0(ctx, {}), instanceId={}";
        var rawCallId = suplaDataPacket.callId();
        if (rawCallId == SUPLA_DCS_CALL_PING_SERVER.getValue()) {
            // log pings in trace
            LOGGER.trace(msg, suplaDataPacket, uuid);
        } else {
            LOGGER.debug(msg, suplaDataPacket, uuid);
        }
        val callTypeOptional = callTypeParser.parse(rawCallId);
        if (callTypeOptional.isEmpty()) {
            LOGGER.debug("Cannot find call type for {}, instanceId={}", rawCallId, uuid);
            return;
        }
        val callType = callTypeOptional.get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        val data = suplaDataPacket.data();
        LOGGER.trace(
                "Decoding data with decoder {}:\n{}, instanceId={}",
                decoder.getClass().getName(),
                data,
                uuid);
        val entity = decoder.decode(data);
        if (suplaDataPacket.dataSize() != entity.protoSize()
                // because the size of SuplaTimeval varies, we are not checking this
                && !callType.equals(SUPLA_DCS_CALL_PING_SERVER)) {
            LOGGER.warn(
                    "WTF?! The size of the entity is different than `dataSize` from"
                        + " `suplaDataPacket.dataSize`. Looks like a bug in the Supla decoder"
                        + " implementation. entity.size={}, suplaDataPacket.dataSize={}, entity={},"
                        + " suplaDataPacket={}, instanceId={}",
                    entity.protoSize(),
                    suplaDataPacket.dataSize(),
                    entity,
                    suplaDataPacket,
                    uuid);
        }
        if (!(entity instanceof ToServerProto toServerProto)) {
            LOGGER.warn(
                    "Why device/client has send a proto that do not extends {}? "
                            + "Looks like a bug... "
                            + "entity.class={}, entity={}, instanceId={}",
                    ToServerProto.class.getName(),
                    entity.getClass(),
                    entity,
                    uuid);
            return;
        }

        messageHandler.handle(toServerProto);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.debug(
                "SuplaHandler.exceptionCaught(ctx, {}, instanceId={})",
                cause.getLocalizedMessage(),
                uuid,
                cause);
        messageHandler.socketException(cause);
        ctx.close();
    }
}
