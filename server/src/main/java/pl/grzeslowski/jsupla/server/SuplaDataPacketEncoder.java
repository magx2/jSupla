package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;
import static pl.grzeslowski.jsupla.server.NettyServer.NOISY_CALL_TYPE_IDS;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

final class SuplaDataPacketEncoder extends MessageToByteEncoder<SuplaDataPacket> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuplaDataPacketEncoder.class);
    private final String uuid;

    public SuplaDataPacketEncoder(String uuid) {
        this.uuid = uuid;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, SuplaDataPacket msg, ByteBuf out) {
        if (NOISY_CALL_TYPE_IDS.contains(msg.callId())) {
            // log pings in trace
            LOGGER.trace(
                    "[{}] SuplaDataPacketEncoder.encode(ctx, {}, out)"
                            + " (SUPLA_SDC_CALL_PING_SERVER_RESULT)",
                    uuid,
                    msg);
        } else {
            LOGGER.debug("[{}] SuplaDataPacketEncoder.encode(ctx, {}, out)", uuid, msg);
        }
        out.writeBytes(SUPLA_TAG)
                .writeByte((byte) msg.version())
                .writeIntLE((int) msg.rrId())
                .writeIntLE((int) msg.callId())
                .writeIntLE((int) msg.dataSize())
                .writeBytes(msg.data())
                .writeBytes(SUPLA_TAG);
    }
}
