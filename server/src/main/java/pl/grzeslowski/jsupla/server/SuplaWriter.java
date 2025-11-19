package pl.grzeslowski.jsupla.server;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_PROTO_VERSION;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;

@Slf4j
@RequiredArgsConstructor
public class SuplaWriter {
    private final AtomicLong msgId;
    private final EncoderFactory encoderFactory;
    @NonNull private final ChannelHandlerContext context;
    @Setter @Getter private short version = SUPLA_PROTO_VERSION;

    public ChannelFuture write(@Nonnull FromServerProto proto) {
        val encoder = encoderFactory.getEncoder(proto);
        val encode = encoder.encode(proto);
        val packet =
                new SuplaDataPacket(
                        version,
                        msgId.getAndIncrement(),
                        proto.callType().getValue(),
                        encode.length,
                        encode);
        if (packet.callId() == SUPLA_SDC_CALL_PING_SERVER_RESULT.getValue()) {
            // log pings in trace
            log.trace("ctx.writeAndFlush({}) (ping result)", packet);
        } else {
            log.debug("ctx.writeAndFlush({})", packet);
        }
        return context.writeAndFlush(packet);
    }
}
