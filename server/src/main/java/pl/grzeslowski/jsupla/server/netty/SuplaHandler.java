package pl.grzeslowski.jsupla.server.netty;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jakarta.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicLong;
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
import pl.grzeslowski.jsupla.server.api.MessageHandler;
import pl.grzeslowski.jsupla.server.api.Writer;

final class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket> {
    private final Logger logger;
    private final AtomicLong msgId = new AtomicLong(1);
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;
    private final MessageHandler messageHandler;

    private final AtomicReference<ChannelHandlerContext> context = new AtomicReference<>();
    private final AtomicReference<Writer> writer = new AtomicReference<>();

    SuplaHandler(
            CallTypeParser callTypeParser,
            DecoderFactory decoderFactory,
            EncoderFactory encoderFactory,
            MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        logger = LoggerFactory.getLogger(SuplaHandler.class.getName() + "#" + hashCode());
        logger.debug("New instance");
        this.callTypeParser = callTypeParser;
        this.decoderFactory = decoderFactory;
        this.encoderFactory = encoderFactory;
    }

    @Override
    public void channelActive(@Nonnull ChannelHandlerContext ctx) throws Exception {
        logger.debug("SuplaHandler.channelActive(ctx)");
        super.channelActive(ctx);
        context.set(ctx);
        val writer = new ChannelHandlerContextWriter(msgId, encoderFactory, context);
        this.writer.set(writer);
        messageHandler.active(writer);
    }

    @Override
    public void channelInactive(@Nonnull ChannelHandlerContext ctx) throws Exception {
        logger.debug("SuplaHandler.channelInactive(ctx)");
        messageHandler.inactive();
        context.set(null);
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
        val msg = "SuplaHandler.channelRead0(ctx, {})";
        if (suplaDataPacket.callId() == SUPLA_DCS_CALL_PING_SERVER.getValue()) {
            // log pings in trace
            logger.trace(msg, suplaDataPacket);
        } else {
            logger.debug(msg, suplaDataPacket);
        }
        val callTypeOptional = callTypeParser.parse(suplaDataPacket.callId());
        if (!callTypeOptional.isPresent()) {
            // warning message was already logged
            return;
        }
        val callType = callTypeOptional.get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        val data = suplaDataPacket.data();
        logger.trace("Decoding data with decoder {}:\n{}", decoder.getClass().getName(), data);
        val entity = decoder.decode(data);
        if (suplaDataPacket.dataSize() != entity.protoSize()
                && !callType.equals(
                        SUPLA_DCS_CALL_PING_SERVER) // because the size of SuplaTimeval varies, we
        // are not checking this
        ) {
            logger.warn(
                    "WTF?! The size of the entity is different than `dataSize` from"
                        + " `suplaDataPacket.dataSize`. Looks like a bug in the Supla decoder"
                        + " implementation. entity.size={}, suplaDataPacket.dataSize={}, entity={},"
                        + " suplaDataPacket={}",
                    entity.protoSize(),
                    suplaDataPacket.dataSize(),
                    entity,
                    suplaDataPacket);
        }
        if (!ToServerProto.class.isAssignableFrom(entity.getClass())) {
            logger.warn(
                    "Why device/client has send a proto that do not extends {}? "
                            + "Looks like a bug... "
                            + "entity.class={}, entity={}",
                    ToServerProto.class.getName(),
                    entity.getClass(),
                    entity);
            return;
        }

        messageHandler.handle((ToServerProto) entity);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        messageHandler.socketException(cause);
        ctx.close();
    }
}
