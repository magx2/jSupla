package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

final class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket>
    implements Publisher<SuplaDataPacket>, AutoCloseable {
    private final Logger logger;
    private final Collection<FluxSink<NettyChannel>> rootEmitters;

    // For NettyChannel
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;

    private final Flux<SuplaDataPacket> flux;
    private final Collection<FluxSink<SuplaDataPacket>> emitters = Collections.synchronizedList(new LinkedList<>());
    private final Disposable disposable;

    SuplaHandler(final Collection<FluxSink<NettyChannel>> rootEmitters,
                 final CallTypeParser callTypeParser,
                 final DecoderFactory decoderFactory,
                 final EncoderFactory encoderFactory) {
        logger = LoggerFactory.getLogger(SuplaHandler.class.getName() + "#" + hashCode());
        logger.debug("New instance");
        this.rootEmitters = rootEmitters;
        this.callTypeParser = callTypeParser;
        this.decoderFactory = decoderFactory;
        this.encoderFactory = encoderFactory;
        {
            //@ 8.1 https://www.baeldung.com/reactor-core
            final ConnectableFlux<SuplaDataPacket> flux = Flux.<SuplaDataPacket>create(emitter -> {
                this.emitters.add(emitter);
                emitter.onDispose(() -> this.emitters.remove(emitter));
            }).publish();
            this.disposable = flux.connect();
            this.flux = flux;
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.debug("SuplaHandler.handlerRemoved(ctx)");
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        rootEmitters.forEach(emitter -> emitter.next(newNettyChannel(ctx, flux)));
    }

    private NettyChannel newNettyChannel(final ChannelHandlerContext ctx, final Flux<SuplaDataPacket> flux) {
        return new NettyChannel(ctx, flux, callTypeParser, decoderFactory, encoderFactory);
    }

    @Override
    public void subscribe(final Subscriber<? super SuplaDataPacket> subscriber) {
        flux.subscribe(subscriber);
    }

    @Override
    public void channelUnregistered(final ChannelHandlerContext ctx) throws Exception {
        logger.debug("SuplaHandler.channelUnregistered(ctx), emitters.size={}", emitters.size());
        super.channelUnregistered(ctx);
        emitters.forEach(FluxSink::complete);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        logger.debug("SuplaHandler.exceptionCaught(ctx, {})", cause);
        super.exceptionCaught(ctx, cause);
        emitters.forEach(e -> e.error(cause));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("SuplaHandler.channelInactive(ctx), emitters.size={}", emitters.size());
        super.channelInactive(ctx);
        emitters.forEach(FluxSink::complete);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SuplaDataPacket msg) throws Exception {
        logger.trace("SuplaHandler.channelRead0(ctx, {})", msg);
        emitters.forEach(emitter -> emitter.next(msg));
        ctx.flush();
    }

    @Override
    public void close() {
        logger.debug("Closing SuplaHandler");
        emitters.clear();
        disposable.dispose();
    }
}
