package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private final Supplier<SuplaHandler> suplaHandler;
    private final SslContext sslCtx;


    public NettyServerInitializer(Supplier<SuplaHandler> suplaHandler, SslContext sslCtx) {
        this.suplaHandler = requireNonNull(suplaHandler);
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }

        // Add the text line codec combination first,
//        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // the encoder and decoder are static as these are sharable
        pipeline.addLast(new SuplaDataPacketDecoder());
        pipeline.addLast(new SuplaDataPacketEncoder());

        // and then business logic.
        pipeline.addLast(suplaHandler.get());
    }
}
