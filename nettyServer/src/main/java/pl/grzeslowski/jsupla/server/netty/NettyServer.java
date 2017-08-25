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
import pl.grzeslowski.jsupla.server.ents.SuplaNewConnection;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("ALL") // FIXME temporary
public class NettyServer implements Server {
    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private final AtomicBoolean started = new AtomicBoolean();
    private final NettyConfig nettyConfig;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ChannelFuture channelFuture;
    private SuplaHandler suplaHandler;

    public NettyServer(NettyConfig nettyConfig) {
        this.nettyConfig = requireNonNull(nettyConfig);
    }

    @Override
    public Flux<SuplaNewConnection> run() throws Exception {
        logger.debug("NettyServer.run()");
        if (started.getAndSet(true)) {
            throw new IllegalStateException("Server can be started only once!");
        }

        SelfSignedCertificate ssc = new SelfSignedCertificate();
        SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

        bossGroup = new NioEventLoopGroup(); // (1)
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap(); // (2)

        final NettyServerInitializer nettyServerInitializer = new NettyServerInitializer(sslCtx);
        final Flux<SuplaNewConnection> suplaNewConnectionPublisher = Flux.create(emitter -> {
            nettyServerInitializer.addOnNewConnectionListener(emitter);
            emitter.onDispose(() -> nettyServerInitializer.removeOnNewConnectionListener(emitter));
        });

        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(nettyServerInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

        // Bind and start to accept incoming connections.
        logger.trace("Binding to port {}", nettyConfig.getPort());
        channelFuture = b.bind(nettyConfig.getPort());

        return suplaNewConnectionPublisher;
    }

    @Override
    public void close() throws Exception {
        if (!started.get()) {
            throw new IllegalArgumentException("Not started!");
        }
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
