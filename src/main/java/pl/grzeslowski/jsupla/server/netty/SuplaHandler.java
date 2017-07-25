package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.server.SuplaDataPacketDispatcher;

import static java.util.Objects.requireNonNull;

class SuplaHandler extends SimpleChannelInboundHandler<TSuplaDataPacket> {
    private final Logger logger = LoggerFactory.getLogger(SuplaHandler.class);
    private final SuplaDataPacketDispatcher dispatcher;

    SuplaHandler(SuplaDataPacketDispatcher dispatcher) {
        this.dispatcher = requireNonNull(dispatcher);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TSuplaDataPacket msg) throws Exception {
        logger.trace("Got {}", msg);
        dispatcher.dispatch(msg);
        // Calculate the cumulative factorial and send it to the client.
//          lastMultiplier = msg;
//          factorial = factorial.multiply(msg);
//          ctx.writeAndFlush(factorial);
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
