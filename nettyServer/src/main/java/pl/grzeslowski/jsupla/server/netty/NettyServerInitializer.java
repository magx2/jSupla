package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageChannel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.synchronizedList;

class NettyServerInitializer extends ChannelInitializer<SocketChannel>
        implements Publisher<Publisher<SuplaDataPackageChannel>> {
    private final Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    private final SslContext sslCtx;
    private final Flux<Publisher<SuplaDataPackageChannel>> flux;
    // TODO maybe not synchronized?
    private List<FluxSink<Publisher<SuplaDataPackageChannel>>> emitters = synchronizedList(new LinkedList<>());

    NettyServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
        this.flux = Flux.create(emitter -> {
            NettyServerInitializer.this.emitters.add(emitter);
            emitter.onDispose(() -> NettyServerInitializer.this.emitters.remove(emitter));
        });
    }

    @Override
    public void subscribe(final Subscriber<? super Publisher<SuplaDataPackageChannel>> subscriber) {
        flux.subscribe(subscriber);
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
        pipeline.addLast(new SuplaHandler());
    }
}
