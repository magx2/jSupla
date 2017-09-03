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
import static java.util.Collections.unmodifiableCollection;

@SuppressWarnings("WeakerAccess")
class NettyServerInitializer extends ChannelInitializer<SocketChannel>
        implements Publisher<ChannelAndSuplaDataPackageFlux> {
    private final Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    private final SslContext sslCtx;
    private final Flux<ChannelAndSuplaDataPackageFlux> flux;
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
        logger.debug("Initializing new channel");
        ChannelPipeline pipeline = ch.pipeline();

        initPipeline(pipeline);
        addSslContext(ch, pipeline);
        addDelimiterBasedFrameDecoder(pipeline);
        addDecoder(pipeline);
        addEncoder(pipeline);
        addHandler(pipeline);
        initLastPipeline(pipeline);
    }

    /**
     * This method can be overloaded to initialize pipeline before (@link {@link NettyServerInitializer} does.
     */
    @SuppressWarnings("unused")
    protected void initPipeline(final ChannelPipeline pipeline) {

    }

    protected void addSslContext(final SocketChannel ch, final ChannelPipeline pipeline) {
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
    }

    protected void addDelimiterBasedFrameDecoder(final ChannelPipeline pipeline) {
        // TODO add DelimiterBasedFrameDecoder
    }

    protected void addDecoder(final ChannelPipeline pipeline) {
        pipeline.addLast(new SuplaDataPacketDecoder());
    }

    protected void addEncoder(final ChannelPipeline pipeline) {
        pipeline.addLast(new SuplaDataPacketEncoder());
    }

    protected void addHandler(final ChannelPipeline pipeline) {
        pipeline.addLast(new SuplaHandler(unmodifiableCollection(emitters)));
    }

    /**
     * This method can be overloaded to initialize pipeline after (@link {@link NettyServerInitializer} does.
     */
    @SuppressWarnings("unused")
    protected void initLastPipeline(final ChannelPipeline pipeline) {

    }
}
