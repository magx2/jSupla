package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_PROTO_VERSION;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicLong;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;

@Slf4j
@RequiredArgsConstructor
final class SuplaDefaultWriter implements SuplaWriter {
    private final AtomicLong msgId = new AtomicLong(1);
    private final EncoderFactory encoderFactory;
    @NonNull private final ChannelHandlerContext context;
    @Getter private short version = SUPLA_PROTO_VERSION;

    void setVersion(short version) {
        if (version > SUPLA_PROTO_VERSION) {
            log.warn(
                    "Supla version {} is higher than SUPLA_PROTO_VERSION ({})!",
                    version,
                    SUPLA_PROTO_VERSION);
        }
        this.version = version < SUPLA_PROTO_VERSION ? version : SUPLA_PROTO_VERSION;
    }

    @Override
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
            log.trace("ctx.writeAndFlush({}) (SUPLA_SDC_CALL_PING_SERVER_RESULT)", packet);
        } else {
            log.debug("ctx.writeAndFlush({})", packet);
        }
        return context.writeAndFlush(packet);
    }
}
