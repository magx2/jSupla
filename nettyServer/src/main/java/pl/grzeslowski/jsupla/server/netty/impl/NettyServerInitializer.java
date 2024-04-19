package pl.grzeslowski.jsupla.server.netty.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.ssl.SslContext;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.synchronizedList;
import static java.util.Collections.unmodifiableCollection;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

@SuppressWarnings("WeakerAccess")
class NettyServerInitializer extends ChannelInitializer<SocketChannel>
    implements Publisher<NettyChannel>, AutoCloseable {
    private final Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    private final SslContext sslCtx;
    private final Flux<NettyChannel> flux;
    private final Collection<FluxSink<NettyChannel>> emitters = synchronizedList(new LinkedList<>());

    // For SuplaHandler
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;
    private final Disposable disposable;
    private final Collection<SuplaHandler> suplaHandlers = synchronizedList(new LinkedList<>());

    NettyServerInitializer(SslContext sslCtx,
                           final CallTypeParser callTypeParser,
                           final DecoderFactory decoderFactory,
                           final EncoderFactory encoderFactory) {
        logger.debug("New instance #{}", hashCode());
        this.sslCtx = sslCtx;
        this.callTypeParser = callTypeParser;
        this.decoderFactory = decoderFactory;
        this.encoderFactory = encoderFactory;
        // @ 8.1 https://www.baeldung.com/reactor-core
        final ConnectableFlux<NettyChannel> flux = Flux.<NettyChannel>create(emitter -> {
            this.emitters.add(emitter);
            emitter.onDispose(() -> this.emitters.remove(emitter));
        }).publish();
        disposable = flux.connect();
        this.flux = flux;
    }

    @Override
    public void subscribe(final Subscriber<? super NettyChannel> subscriber) {
        flux.subscribe(subscriber);
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        logger.debug("Initializing new channel #{}", hashCode());
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
        pipeline.addLast(new DelimiterBasedFrameDecoder(
            SUPLA_MAX_DATA_SIZE,
            false,
            true,
            Unpooled.copiedBuffer(SUPLA_TAG)
        ));
    }

    protected void addDecoder(final ChannelPipeline pipeline) {
        pipeline.addLast(new SuplaDataPacketDecoder());
    }

    protected void addEncoder(final ChannelPipeline pipeline) {
        pipeline.addLast(new SuplaDataPacketEncoder());
    }

    protected void addHandler(final ChannelPipeline pipeline) {
        SuplaHandler suplaHandler = new SuplaHandler(
            unmodifiableCollection(emitters),
            callTypeParser,
            decoderFactory,
            encoderFactory);
        pipeline.addLast(suplaHandler);
        suplaHandlers.add(suplaHandler);
    }

    /**
     * This method can be overloaded to initialize pipeline after (@link {@link NettyServerInitializer} does.
     */
    @SuppressWarnings("unused")
    protected void initLastPipeline(final ChannelPipeline pipeline) {

    }

    @Override
    public void close() {
        logger.debug("Closing NettyServerInitializer #{}", hashCode());
        disposable.dispose();
        List<SuplaHandler> local = new ArrayList<>(suplaHandlers);
        suplaHandlers.clear();
        for (SuplaHandler suplaHandler : local) {
            try {
                suplaHandler.close();
            } catch (Exception e) {
                logger.warn("Error closing SuplaHandler #{}",suplaHandler.hashCode(), e);
            }
        }
    }
}
