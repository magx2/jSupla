package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndSuplaDataPackageFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collection;
import java.util.LinkedList;

import static java.util.Collections.synchronizedList;

class NettyServerInitializer extends ChannelInitializer<SocketChannel>
        implements Publisher<ChannelAndSuplaDataPackageFlux> {
    private final Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    private final SslContext sslCtx;
    private final Flux<ChannelAndSuplaDataPackageFlux> flux;
    // TODO maybe not synchronized?
    private final Collection<FluxSink<ChannelAndSuplaDataPackageFlux>> emitters =
            synchronizedList(new LinkedList<>());

    NettyServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
        this.flux = Flux.create(emitter -> {
            NettyServerInitializer.this.emitters.add(emitter);
            emitter.onDispose(() -> NettyServerInitializer.this.emitters.remove(emitter));
        });
    }

    @Override
    public void subscribe(final Subscriber<? super ChannelAndSuplaDataPackageFlux> subscriber) {
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
        pipeline.addLast(new SuplaHandler(emitters)); // TODO do new unmodificalbe field so it cant be chaned by user
    }
}
