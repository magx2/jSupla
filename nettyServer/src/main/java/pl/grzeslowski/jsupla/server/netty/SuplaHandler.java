package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.SuplaConnection;
import pl.grzeslowski.jsupla.server.SuplaNewConnection;
import pl.grzeslowski.jsupla.server.entities.requests.Request;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);
    private final NotificationAboutNewChannel notificationAboutNewChannel;
    private Flux<SuplaConnection> flux;
    private List<FluxSink<SuplaConnection>> emitters = Collections.synchronizedList(new LinkedList<>());

    SuplaHandler(final NotificationAboutNewChannel notificationAboutNewChannel) {
        this.notificationAboutNewChannel = notificationAboutNewChannel;
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        logger.info("SuplaHandler.channelRegistered(ctx)");
        super.channelRegistered(ctx);

        this.flux = Flux.create(emitter -> {
            SuplaHandler.this.emitters.add(emitter);
            emitter.onDispose(() -> SuplaHandler.this.emitters.remove(emitter));
        });
        final SuplaNewConnection suplaNewConnection =
                new SuplaNewConnection(flux, null, ctx::write);
        notificationAboutNewChannel.notify(suplaNewConnection);
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
        final SuplaConnection suplaConnection = newSuplaConnection(msg, ctx);
        emitters.forEach(e -> e.next(suplaConnection));
    }

    private SuplaConnection newSuplaConnection(SuplaDataPacket msg, ChannelHandlerContext ctx) {
        return new SuplaConnection(parseSuplaDataPacket(msg), ctx::write);
    }

    private Request parseSuplaDataPacket(final SuplaDataPacket msg) {
//        throw new UnsupportedOperationException("TODO implement me");
        return null;
    }
}
