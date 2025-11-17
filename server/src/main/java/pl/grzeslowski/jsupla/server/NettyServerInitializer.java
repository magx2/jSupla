package pl.grzeslowski.jsupla.server;

import static java.util.concurrent.TimeUnit.SECONDS;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.timeout.ReadTimeoutHandler;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;

@SuppressWarnings("WeakerAccess")
public final class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private final Logger logger;

    @Nullable private final SslContext sslCtx;
    private final long readTimeoutSeconds;

    // For SuplaHandler
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;
    private final MessageHandlerFactory messageHandlerFactory;

    NettyServerInitializer(
            @Nullable SslContext sslCtx,
            long readTimeoutSeconds,
            CallTypeParser callTypeParser,
            DecoderFactory decoderFactory,
            EncoderFactory encoderFactory,
            MessageHandlerFactory messageHandlerFactory) {
        logger = LoggerFactory.getLogger(this.getClass().getName() + "#" + hashCode());
        logger.debug("New instance");
        this.sslCtx = sslCtx;
        this.readTimeoutSeconds = readTimeoutSeconds;
        this.callTypeParser = callTypeParser;
        this.decoderFactory = decoderFactory;
        this.encoderFactory = encoderFactory;
        this.messageHandlerFactory = messageHandlerFactory;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        logger.debug(
                "Initializing new channel, localAddress={}, remoteAddress={}",
                ch.localAddress(),
                ch.remoteAddress());
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(new ReadTimeoutHandler(readTimeoutSeconds, SECONDS));
        pipeline.addLast(
                new DelimiterBasedFrameDecoder(
                        SUPLA_MAX_DATA_SIZE, false, true, Unpooled.copiedBuffer(SUPLA_TAG)));
        pipeline.addLast(new SuplaDataPacketDecoder());
        pipeline.addLast(new SuplaDataPacketEncoder());
        pipeline.addLast(
                new SuplaHandler(
                        callTypeParser,
                        decoderFactory,
                        encoderFactory,
                        messageHandlerFactory.create(ch)));
    }
}
