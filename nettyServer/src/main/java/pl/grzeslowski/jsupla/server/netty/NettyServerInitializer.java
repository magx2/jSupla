package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

import java.util.concurrent.ExecutorService;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private final ExecutorService executorService;
    private final SslContext sslCtx;

    public NettyServerInitializer(ExecutorService executorService, SslContext sslCtx) {
        this.executorService = executorService;
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }

        // TODO add DelimiterBasedFrameDecoder
        pipeline.addLast(new SuplaDataPacketDecoder());
        pipeline.addLast(new SuplaDataPacketEncoder());

        // and then business logic.
        pipeline.addLast(new SuplaHandler(executorService));
    }
}
