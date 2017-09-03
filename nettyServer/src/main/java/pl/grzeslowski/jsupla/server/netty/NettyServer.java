package pl.grzeslowski.jsupla.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.NotStartedException;
import pl.grzeslowski.jsupla.server.Server;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndSuplaDataPackageFlux;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("WeakerAccess")
public class NettyServer implements Server {
    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private final AtomicBoolean started = new AtomicBoolean();
    private final NettyConfig nettyConfig;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ChannelFuture channelFuture;

    public NettyServer(NettyConfig nettyConfig) {
        this.nettyConfig = requireNonNull(nettyConfig);
    }

    @Override
    public Publisher<ChannelAndSuplaDataPackageFlux> run() throws Exception {
        logger.debug("NettyServer.run()");
        if (started.getAndSet(true)) {
            throw new IllegalStateException("Server can be started only once!");
        }

        bossGroup = newBossGroup();
        workerGroup = newWorkerGroup();
        final ServerBootstrap serverBootstrap = newServerBootstrap();
        final NettyServerInitializer nettyServerInitializer = newNettyServerInitializer();

        logger.trace("Configuting server bootstrap");
        configureServerBootstrap(serverBootstrap, nettyServerInitializer);

        // Bind and start to accept incoming connections.
        logger.trace("Binding to port {}", nettyConfig.getPort());
        channelFuture = serverBootstrap.bind(nettyConfig.getPort());

        return nettyServerInitializer;
    }
    protected NioEventLoopGroup newWorkerGroup() {
        return new NioEventLoopGroup();
    }

    protected NioEventLoopGroup newBossGroup() {
        return new NioEventLoopGroup();
    }

    protected ServerBootstrap newServerBootstrap() {
        return new ServerBootstrap();
    }

    protected NettyServerInitializer newNettyServerInitializer() {
        return new NettyServerInitializer(nettyConfig.getSslCtx());
    }

    protected void configureServerBootstrap(final ServerBootstrap serverBootstrap,
                                            final NettyServerInitializer nettyServerInitializer) {
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(nettyServerInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
    }

    @Override
    public void close() throws Exception {
        if (!started.get()) {
            throw new NotStartedException();
        }
        try {
            logger.debug("Closing channels");
            channelFuture.channel().closeFuture().sync();
        } finally {
            logger.debug("Closing workerGroup and bossGroup");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
