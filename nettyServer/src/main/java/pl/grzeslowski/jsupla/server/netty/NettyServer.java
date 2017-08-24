package pl.grzeslowski.jsupla.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.Server;
import pl.grzeslowski.jsupla.server.SuplaNewConnection;
import pl.grzeslowski.jsupla.server.dispatchers.SuplaDataPacketDispatcher;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("ALL") // FIXME temporary
public class NettyServer implements Server {
    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private final AtomicBoolean started = new AtomicBoolean();
    private final NettyConfig nettyConfig;
    private final SuplaDataPacketDispatcher suplaDataPacketDispatcher;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ChannelFuture channelFuture;
    private SuplaHandler suplaHandler;
    private Flux<SuplaNewConnection> suplaNewConnectionPublisher;

    public NettyServer(NettyConfig nettyConfig, SuplaDataPacketDispatcher suplaDataPacketDispatcher) {
        this.nettyConfig = requireNonNull(nettyConfig);
        this.suplaDataPacketDispatcher = requireNonNull(suplaDataPacketDispatcher);
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

        final NettyServerInitializer nettyServerInitializer = new NettyServerInitializer(sslCtx);
        this.suplaNewConnectionPublisher = Flux.create(emitter -> {
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

    @Override
    public void subscribe(final Subscriber<? super SuplaNewConnection> subscriber) {
        if (!started.get()) {
            throw new IllegalArgumentException("Server not started!");
        }
        suplaNewConnectionPublisher.subscribe(subscriber);
    }
}
