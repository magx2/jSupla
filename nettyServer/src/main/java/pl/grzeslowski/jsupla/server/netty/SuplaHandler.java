package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.dispatchers.SuplaDataPacketDispatcher;

import static java.util.Objects.requireNonNull;

@ChannelHandler.Sharable
class SuplaHandler extends SimpleChannelInboundHandler<SuplaDataPacket> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);
    private final SuplaDataPacketDispatcher dispatcher;

    SuplaHandler(SuplaDataPacketDispatcher dispatcher) {
        this.dispatcher = requireNonNull(dispatcher);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SuplaDataPacket msg) throws Exception {
        logger.trace("Got {}", msg);
        dispatcher.dispatch(msg).ifPresent(dataPacket -> sendTSuplaDataPacket(ctx, dataPacket));
    }

    @SuppressWarnings("WeakerAccess")
    protected void sendTSuplaDataPacket(ChannelHandlerContext ctx, SuplaDataPacket dataPacket) {
        logger.trace("Sending {}", dataPacket);
        ctx.writeAndFlush(dataPacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.trace("channelInactive {}", ctx.name());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("ExceptionCaught in SuplaHandler", cause); // TODO better exception handling
        ctx.close();
    }
}
