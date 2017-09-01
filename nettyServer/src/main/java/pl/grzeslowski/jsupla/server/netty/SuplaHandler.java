package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.SuplaChannel;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageConnection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket>
        implements Publisher<SuplaDataPackageConnection> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);
    private Flux<SuplaDataPackageConnection> flux;// TODO maybe not synchronized?
    private List<FluxSink<SuplaDataPackageConnection>> emitters = Collections.synchronizedList(new LinkedList<>());

    SuplaHandler() {
        this.flux = Flux.create(emitter -> {
            SuplaHandler.this.emitters.add(emitter);
            emitter.onDispose(() -> SuplaHandler.this.emitters.remove(emitter));
        });
    }

    @Override
    public void subscribe(final Subscriber<? super SuplaDataPackageConnection> subscriber) {
        flux.subscribe(subscriber);
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        logger.info("SuplaHandler.channelInactive(ctx)");
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
        logger.trace("Got {}", msg);
        final SuplaDataPackageConnection suplaConnection = newSuplaConnection(msg, ctx);
        emitters.forEach(emitter -> emitter.next(suplaConnection));
        ctx.flush(); // TODO maybe use it here
    }

    private SuplaDataPackageConnection newSuplaConnection(SuplaDataPacket msg, ChannelHandlerContext ctx) {
        return new SuplaDataPackageConnection(msg, newSuplaChannel(ctx));
    }

    private SuplaChannel newSuplaChannel(final ChannelHandlerContext ctx) {
        return (SuplaDataPacket msg) -> {
            logger.info("SuplaHandler.newSuplaChannel(" + msg + ")");
            ctx.write(msg);
        };
    }
}
