package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

import static java.util.Objects.requireNonNull;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SuplaHandler suplaHandler;

    private final SuplaDataPacketDecoder decoder;
    private final SuplaDataPacketEncoder encoder;

    private final SslContext sslCtx;


    public NettyServerInitializer(SuplaHandler suplaHandler, SuplaDataPacketDecoder decoder, SuplaDataPacketEncoder encoder, SslContext sslCtx) {
        this.suplaHandler = requireNonNull(suplaHandler);
        this.decoder = requireNonNull(decoder);
        this.encoder = requireNonNull(encoder);
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }

        // the encoder and decoder are static as these are sharable
        pipeline.addLast(decoder);
        pipeline.addLast(encoder);

        // and then business logic.
        pipeline.addLast(suplaHandler);
    }
}
