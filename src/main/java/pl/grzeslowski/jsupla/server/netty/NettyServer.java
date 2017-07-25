package pl.grzeslowski.jsupla.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pl.grzeslowski.jsupla.server.Server;
import pl.grzeslowski.jsupla.server.listeners.Listeners;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.requireNonNull;

public class NettyServer implements Server {
    private final AtomicBoolean started = new AtomicBoolean();
    private final NettyConfig nettyConfig;
    private final Listeners listeners;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ChannelFuture channelFuture;

    public NettyServer(NettyConfig nettyConfig, Listeners listeners) {
        this.nettyConfig = requireNonNull(nettyConfig);
        this.listeners = requireNonNull(listeners);
    }

    @Override
    public void run() throws Exception {
        if (started.getAndSet(true)) {
            throw new IllegalStateException("Server can be started only once!");
        }

        bossGroup = new NioEventLoopGroup(); // (1)
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap(); // (2)
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SuplaDataPacketDecoder(), new SuplaDataPacketEncoder(), new SuplaHandler(listeners));
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

        // Bind and start to accept incoming connections.
        channelFuture = b.bind(nettyConfig.getPort());
    }

    @Override
    public void close() throws Exception {
        try {
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
