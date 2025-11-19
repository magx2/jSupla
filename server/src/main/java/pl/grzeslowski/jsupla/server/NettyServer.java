package pl.grzeslowski.jsupla.server;

import static java.util.Objects.requireNonNull;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import lombok.ToString;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;

@SuppressWarnings("WeakerAccess")
@ToString(onlyExplicitlyIncluded = true)
public final class NettyServer implements AutoCloseable {
    private static final AtomicLong ID = new AtomicLong();
    private final Logger logger;

    @ToString.Include private final NettyConfig nettyConfig;
    private final NioEventLoopGroup bossGroup;
    private final NioEventLoopGroup workerGroup;
    private final ChannelFuture channelFuture;

    public NettyServer(
            NettyConfig nettyConfig,
            CallTypeParser callTypeParser,
            DecoderFactory decoderFactory,
            EncoderFactory encoderFactory,
            MessageHandlerFactory messageHandlerFactory) {
        var uuid = ID.incrementAndGet() + "";
        logger = LoggerFactory.getLogger(NettyServer.class.getName() + "#" + uuid);
        logger.debug("New instance {}", getClass().getSimpleName());
        this.nettyConfig = requireNonNull(nettyConfig);

        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        val nettyServerInitializer =
                new NettyServerInitializer(
                        uuid,
                        nettyConfig.sslCtx(),
                        nettyConfig.readTimeoutSeconds(),
                        callTypeParser,
                        decoderFactory,
                        encoderFactory,
                        messageHandlerFactory);

        logger.trace("Configuring server bootstrap");
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(nettyServerInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        // Bind and start to accept incoming connections.
        logger.debug("Binding to port {}", nettyConfig.port());
        channelFuture = serverBootstrap.bind(nettyConfig.port());
    }

    public NettyServer(NettyConfig nettyConfig, MessageHandlerFactory messageHandlerFactory) {
        this(
                nettyConfig,
                CallTypeParser.INSTANCE,
                DecoderFactoryImpl.INSTANCE,
                EncoderFactoryImpl.INSTANCE,
                messageHandlerFactory);
    }

    @Override
    public void close() throws ExecutionException, InterruptedException {
        logger.debug("Closing NettyServer");
        {
            logger.debug("Closing workerGroup");
            workerGroup.shutdownGracefully().get();
            logger.debug("Closed workerGroup");
        }
        {
            logger.debug("Closing bossGroup");
            bossGroup.shutdownGracefully().get();
            logger.debug("Closed bossGroup");
        }
        val channel = channelFuture.channel();
        channel.closeFuture().sync();
        val parent = channel.parent();
        if (parent != null) {
            parent.closeFuture().sync();
        }
    }
}
