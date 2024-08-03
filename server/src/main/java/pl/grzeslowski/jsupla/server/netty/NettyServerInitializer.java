package pl.grzeslowski.jsupla.server.netty;

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
import pl.grzeslowski.jsupla.server.api.MessageHandler;

import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MINUTES;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

@SuppressWarnings("WeakerAccess")
public final class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private final Logger logger;

    @Nullable
    private final SslContext sslCtx;

    // For SuplaHandler
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;
    private final Supplier<MessageHandler> messageHandlerFactory;

    NettyServerInitializer(@Nullable SslContext sslCtx,
                           CallTypeParser callTypeParser,
                           DecoderFactory decoderFactory,
                           EncoderFactory encoderFactory,
                           Supplier<MessageHandler> messageHandlerFactory) {
        logger = LoggerFactory.getLogger(this.getClass().getName() + "#" + hashCode());
        logger.debug("New instance");
        this.sslCtx = sslCtx;
        this.callTypeParser = callTypeParser;
        this.decoderFactory = decoderFactory;
        this.encoderFactory = encoderFactory;
        this.messageHandlerFactory = messageHandlerFactory;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        logger.debug("Initializing new channel, localAddress={}, remoteAddress={}", ch.localAddress(), ch.remoteAddress());
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        // todo 1 min can be parametrized
        // 1 min was used because this is the time between 1 Register event send by the device
        pipeline.addLast(new ReadTimeoutHandler(1, MINUTES));
        pipeline.addLast(new DelimiterBasedFrameDecoder(
            SUPLA_MAX_DATA_SIZE,
            false,
            true,
            Unpooled.copiedBuffer(SUPLA_TAG)
        ));
        pipeline.addLast(new SuplaDataPacketDecoder());
        pipeline.addLast(new SuplaDataPacketEncoder());
        pipeline.addLast(new SuplaHandler(callTypeParser, decoderFactory, encoderFactory, messageHandlerFactory.get()));
    }
}
