package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.api.Writer;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.requireNonNull;

@Slf4j
@RequiredArgsConstructor
class ChannelHandlerContextWriter implements Writer {
    private final AtomicLong msgId;
    private final EncoderFactory encoderFactory;
    private final AtomicReference<ChannelHandlerContext> context;

    @Override
    public ChannelFuture write(@Nonnull FromServerProto proto) {
        val ctx = requireNonNull(context.get(), "Context is null");

        val encoder = encoderFactory.getEncoder(proto);
        val encode = encoder.encode(proto);
        val packet = new SuplaDataPacket(
            (short) 5,
            msgId.getAndIncrement(),
            proto.callType().getValue(),
            encode.length,
            encode);
        return ctx.writeAndFlush(packet);
    }
}
