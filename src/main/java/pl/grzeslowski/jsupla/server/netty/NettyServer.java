package pl.grzeslowski.jsupla.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.Server;
import pl.grzeslowski.jsupla.server.SuplaDataPacketDispatcher;
import pl.grzeslowski.jsupla.server.listeners.Listeners;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.requireNonNull;

public class NettyServer implements Server {
    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private final AtomicBoolean started = new AtomicBoolean();
    private final NettyConfig nettyConfig;
    private final SuplaDataPacketDispatcher suplaDataPacketDispatcher;
    private final Listeners listeners;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ChannelFuture channelFuture;

    public NettyServer(NettyConfig nettyConfig, SuplaDataPacketDispatcher suplaDataPacketDispatcher, Listeners listeners) {
        this.nettyConfig = requireNonNull(nettyConfig);
        this.suplaDataPacketDispatcher = requireNonNull(suplaDataPacketDispatcher);
        this.listeners = requireNonNull(listeners);
    }

    @Override
    public void run() throws Exception {
        logger.debug("NettyServer.run()");
        if (started.getAndSet(true)) {
            throw new IllegalStateException("Server can be started only once!");
        }

        SelfSignedCertificate ssc = new SelfSignedCertificate();
        SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

        bossGroup = new NioEventLoopGroup(); // (1)
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap(); // (2)
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(new NettyServerInitializer(() -> new SuplaHandler(suplaDataPacketDispatcher), sslCtx))
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

        // Bind and start to accept incoming connections.
        channelFuture = b.bind(nettyConfig.getPort());
    }

    @Override
    public void close() throws Exception {
        try {
            logger.debug("Closing channel");
            channelFuture.channel().closeFuture().sync();
        } finally {
            logger.debug("Closing workerGroup and bossGroup");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
