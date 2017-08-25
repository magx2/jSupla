package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sd.SuplaRegisterDeviceResultEncoderImpl;
import pl.grzeslowski.jsupla.server.SuplaChannel;
import pl.grzeslowski.jsupla.server.entities.responses.registerdevice.RegisterDeviceResponse;
import pl.grzeslowski.jsupla.server.ents.SuplaDataPackageConnection;
import pl.grzeslowski.jsupla.server.ents.SuplaNewConnection;
import pl.grzeslowski.jsupla.server.serializers.RegisterDeviceResponseSerializer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);
    private final NotificationAboutNewChannel notificationAboutNewChannel;
    private Flux<SuplaDataPackageConnection> flux;
    private List<FluxSink<SuplaDataPackageConnection>> emitters = Collections.synchronizedList(new LinkedList<>());

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
                new SuplaNewConnection(flux, null, newSuplaChannel(ctx));
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
        final SuplaDataPackageConnection suplaConnection = newSuplaConnection(msg, ctx);
        emitters.forEach(e -> e.next(suplaConnection));
        // ctx.flush(); // TODO maybe use it here
    }

    private SuplaDataPackageConnection newSuplaConnection(SuplaDataPacket msg, ChannelHandlerContext ctx) {
        return new SuplaDataPackageConnection(msg, newSuplaChannel(ctx));
    }

    private SuplaChannel newSuplaChannel(final ChannelHandlerContext ctx) {
        return msg -> {
            final SuplaRegisterDeviceResult result = new RegisterDeviceResponseSerializer().serialize((RegisterDeviceResponse) msg);
            final byte[] encode = new SuplaRegisterDeviceResultEncoderImpl(INSTANCE).encode(result);
            final SuplaDataPacket suplaDataPacket = new SuplaDataPacket((short) 5, 1, result.callType().getValue(), encode.length, encode);
            logger.info("SuplaHandler.newSuplaChannel(" + msg + ")");
            ctx.writeAndFlush(suplaDataPacket);
        };
    }
}
