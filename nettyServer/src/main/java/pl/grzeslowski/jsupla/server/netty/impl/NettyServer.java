package pl.grzeslowski.jsupla.server.netty.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.server.api.Server;
import pl.grzeslowski.jsupla.server.api.exceptions.CloseServerException;
import reactor.core.publisher.Flux;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("WeakerAccess")
public class NettyServer implements Server {
    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private final NettyConfig nettyConfig;
    private final Flux<? extends NettyChannel> newChannelsPipe;
    private final NioEventLoopGroup bossGroup;
    private final NioEventLoopGroup workerGroup;
    private final ChannelFuture channelFuture;

    public NettyServer(final NettyConfig nettyConfig,
                       final CallTypeParser callTypeParser,
                       final DecoderFactory decoderFactory,
                       final EncoderFactory encoderFactory) {
        this.nettyConfig = requireNonNull(nettyConfig);

        bossGroup = newBossGroup();
        workerGroup = newWorkerGroup();
        final ServerBootstrap serverBootstrap = newServerBootstrap();
        final NettyServerInitializer nettyServerInitializer = newNettyServerInitializer(
                callTypeParser, decoderFactory, encoderFactory);

        logger.trace("Configuring server bootstrap");
        configureServerBootstrap(serverBootstrap, nettyServerInitializer);

        // Bind and start to accept incoming connections.
        logger.trace("Binding to port {}", nettyConfig.getPort());
        channelFuture = serverBootstrap.bind(nettyConfig.getPort());
        newChannelsPipe = Flux.from(nettyServerInitializer);
    }

    @Override
    public Flux<? extends NettyChannel> getNewChannelsPipe() {
        return newChannelsPipe;
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

    protected NettyServerInitializer newNettyServerInitializer(
            final CallTypeParser callTypeParser,
            final DecoderFactory decoderFactory,
            final EncoderFactory encoderFactory) {
        return new NettyServerInitializer(
                nettyConfig.getSslCtx(),
                callTypeParser,
                decoderFactory,
                encoderFactory);
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
    public String toString() {
        return "NettyServer{" +
                       "nettyConfig=" + nettyConfig +
                       '}';
    }

    @Override
    public void close() {
        try {
            logger.debug("Closing channels");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new CloseServerException(this, e);
        } finally {
            logger.debug("Closing workerGroup and bossGroup");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
