package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndSuplaDataPackageFlux;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndSuplaDataPackageFluxImpl;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket>
        implements Publisher<SuplaDataPacket> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);
    private final Collection<FluxSink<ChannelAndSuplaDataPackageFlux>> rootEmitters;
    private Flux<SuplaDataPacket> flux;// TODO maybe not synchronized?
    private Collection<FluxSink<SuplaDataPacket>> emitters = Collections.synchronizedList(new LinkedList<>());

    SuplaHandler(final Collection<FluxSink<ChannelAndSuplaDataPackageFlux>> rootEmitters) {
        this.rootEmitters = rootEmitters;
        this.flux = Flux.create(emitter -> {
            SuplaHandler.this.emitters.add(emitter);
            emitter.onDispose(() -> SuplaHandler.this.emitters.remove(emitter));
        });
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        final SuplaDataPacketChannel channel = newSuplaChannel(ctx);
        rootEmitters.forEach(emitter -> emitter.next(new ChannelAndSuplaDataPackageFluxImpl(channel, flux)));
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

    private SuplaDataPacketChannel newSuplaChannel(final ChannelHandlerContext ctx) {
        return (SuplaDataPacket msg) -> {
            logger.debug("SuplaHandler.newSuplaChannel(" + msg + ")");
            ctx.write(msg);
        };
    }
}
