package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jakarta.annotation.Nonnull;
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

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

final class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket> {
    private final Logger logger;
    private final AtomicLong msgId = new AtomicLong(1);
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;
    private final MessageHandler messageHandler;

    private final AtomicReference<ChannelHandlerContext> context = new AtomicReference<>();

    SuplaHandler(CallTypeParser callTypeParser,
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
        messageHandler.active(writer);
    }

    @Override
    public void channelInactive(@Nonnull ChannelHandlerContext ctx) throws Exception {
        logger.debug("SuplaHandler.channelInactive(ctx)");
        messageHandler.inactive();
        context.set(null);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SuplaDataPacket suplaDataPacket) {
        logger.debug("SuplaHandler.channelRead0(ctx, {})", suplaDataPacket);
        val callTypeOptional = callTypeParser.parse(suplaDataPacket.callId);
        if (!callTypeOptional.isPresent()) {
            return;
        }
        val callType = callTypeOptional.get();
        Decoder<? extends ProtoWithSize> decoder = decoderFactory.getDecoder(callType);
        val data = suplaDataPacket.data;
        logger.trace("Decoding data with decoder {}:\n{}", decoder.getClass().getName(), data);
        val entity = decoder.decode(data);
        if (!ToServerProto.class.isAssignableFrom(entity.getClass())) {
            return;
        }

        messageHandler.handle((ToServerProto) entity);
    }
}
