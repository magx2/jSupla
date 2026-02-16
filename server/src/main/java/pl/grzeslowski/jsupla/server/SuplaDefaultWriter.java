package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_PROTO_VERSION;
import static pl.grzeslowski.jsupla.server.NettyServer.NOISY_CALL_TYPE_IDS;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicLong;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;

final class SuplaDefaultWriter implements SuplaWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuplaDefaultWriter.class);
    private final AtomicLong msgId = new AtomicLong(1);
    private final EncoderFactory encoderFactory;
    private final String uuid;
    @NonNull private final ChannelHandlerContext context;
    @Getter private short version = SUPLA_PROTO_VERSION;

    public SuplaDefaultWriter(
            String uuid, EncoderFactory encoderFactory, ChannelHandlerContext context) {
        this.uuid = uuid;
        this.encoderFactory = encoderFactory;
        this.context = context;
    }

    void setVersion(short version) {
        if (version > SUPLA_PROTO_VERSION) {
            LOGGER.warn(
                    "Supla version {} is higher than SUPLA_PROTO_VERSION ({})!, instanceId={}",
                    version,
                    SUPLA_PROTO_VERSION,
                    uuid);
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
        if (NOISY_CALL_TYPE_IDS.contains(packet.callId())) {
            // log pings in trace
            LOGGER.trace(
                    "ctx.writeAndFlush({}) (SUPLA_SDC_CALL_PING_SERVER_RESULT), instanceId={}",
                    proto,
                    uuid);
        } else {
            LOGGER.debug("ctx.writeAndFlush({}), instanceId={}", proto, uuid);
        }
        return context.writeAndFlush(packet);
    }
}
