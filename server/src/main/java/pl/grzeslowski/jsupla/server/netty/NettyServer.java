package pl.grzeslowski.jsupla.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.server.api.Server;
import reactor.core.publisher.Flux;

import java.util.concurrent.ExecutionException;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("WeakerAccess")
@ToString(onlyExplicitlyIncluded = true)
public final class NettyServer implements Server {
    private final Logger logger;

    @ToString.Include
    private final NettyConfig nettyConfig;
    @Getter
    private final Flux<? extends NettyChannel> newChannelsPipe;
    private final NioEventLoopGroup bossGroup;
    private final NioEventLoopGroup workerGroup;
    private final ChannelFuture channelFuture;
    private final NettyServerInitializer nettyServerInitializer;

    public NettyServer(final NettyConfig nettyConfig,
                       final CallTypeParser callTypeParser,
                       final DecoderFactory decoderFactory,
                       final EncoderFactory encoderFactory) {
        logger = LoggerFactory.getLogger(NettyServer.class.getName() + "#" + hashCode());
        logger.debug("New instance");
        this.nettyConfig = requireNonNull(nettyConfig);

        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        nettyServerInitializer = new NettyServerInitializer(
            nettyConfig.getSslCtx(),
            callTypeParser,
            decoderFactory,
            encoderFactory);

        logger.trace("Configuring server bootstrap");
        serverBootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(nettyServerInitializer)
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true); 

        // Bind and start to accept incoming connections.
        logger.debug("Binding to port {}", nettyConfig.getPort());
        channelFuture = serverBootstrap.bind(nettyConfig.getPort());
        newChannelsPipe = Flux.from(nettyServerInitializer);
    }

    @Override
    public void close() throws ExecutionException, InterruptedException {
        logger.debug("Closing NettyServer");
        nettyServerInitializer.close();
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
        Channel channel = channelFuture.channel();
        channel.closeFuture().sync();
        Channel parent = channel.parent();
        if (parent != null) {
            parent.closeFuture().sync();
        }
    }
}
