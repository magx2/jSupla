package pl.grzeslowski.jsupla.server.netty.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket>
        implements Publisher<SuplaDataPacket> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);
    private final Collection<FluxSink<NettyChannel>> rootEmitters;

    // For NettyChannel
    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final Parser<Entity, Proto> parser;

    private Flux<SuplaDataPacket> flux;
    private Collection<FluxSink<SuplaDataPacket>> emitters = Collections.synchronizedList(new LinkedList<>());

    SuplaHandler(final Collection<FluxSink<NettyChannel>> rootEmitters,
                 final CallTypeParser callTypeParser,
                 final DecoderFactory decoderFactory,
                 final Parser<Entity, Proto> parser) {
        this.rootEmitters = rootEmitters;
        this.callTypeParser = callTypeParser;
        this.decoderFactory = decoderFactory;
        this.parser = parser;
        this.flux = Flux.create(emitter -> {
            SuplaHandler.this.emitters.add(emitter);
            emitter.onDispose(() -> SuplaHandler.this.emitters.remove(emitter));
        });
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        rootEmitters.forEach(emitter -> emitter.next(newNettyChannel(ctx, flux)));
    }

    private NettyChannel newNettyChannel(final ChannelHandlerContext ctx, final Flux<SuplaDataPacket> flux) {
        return new NettyChannel(ctx, flux, callTypeParser, decoderFactory, parser);
    }

    @Override
    public void subscribe(final Subscriber<? super SuplaDataPacket> subscriber) {
        flux.subscribe(subscriber);
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        logger.debug("SuplaHandler.channelInactive(ctx)");
        super.channelUnregistered(ctx);
        emitters.forEach(FluxSink::complete);
        dispose();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        emitters.forEach(e -> e.error(cause));
        dispose();
    }

    private void dispose() {
        emitters.clear();
        flux = null;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SuplaDataPacket msg) throws Exception {
        logger.trace("SuplaHandler.channelRead0(ctx, {})", msg);
        emitters.forEach(emitter -> emitter.next(msg));
        ctx.flush();
    }
}
