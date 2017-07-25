package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.server.listeners.Listeners;

import static java.util.Objects.requireNonNull;

class SuplaHandler extends SimpleChannelInboundHandler<TSuplaDataPacket> {

    private final Listeners listeners;

    SuplaHandler(Listeners listeners) {
        this.listeners = requireNonNull(listeners);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TSuplaDataPacket msg) throws Exception {
        listeners.getSuplaDataPacketListener().ifPresent(listener -> listener.onSuplaDataPacket(msg));
        // Calculate the cumulative factorial and send it to the client.
//          lastMultiplier = msg;
//          factorial = factorial.multiply(msg);
//          ctx.writeAndFlush(factorial);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); // TODO better exception handling
        ctx.close();
    }
}
