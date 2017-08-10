package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

import static java.util.Objects.requireNonNull;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SuplaHandler suplaHandler;

    private final SslContext sslCtx;


    public NettyServerInitializer(SuplaHandler suplaHandler, SslContext sslCtx) {
        this.suplaHandler = requireNonNull(suplaHandler);
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }

        pipeline.addLast(new SuplaDataPacketDecoder());
        pipeline.addLast(new SuplaDataPacketEncoder());

        // and then business logic.
        pipeline.addLast(suplaHandler);
    }
}
