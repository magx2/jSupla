package pl.grzeslowski.jsupla.server;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;
import static pl.grzeslowski.jsupla.server.NettyServer.NOISY_CALL_TYPE_IDS;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

final class SuplaDataPacketEncoder extends MessageToByteEncoder<SuplaDataPacket> {
    private static final AtomicLong ID = new AtomicLong();
    private final Logger log;

    public SuplaDataPacketEncoder(String uuid) {
        log =
                LoggerFactory.getLogger(
                        SuplaDataPacketEncoder.class.getName()
                                + "#"
                                + uuid
                                + ":"
                                + ID.incrementAndGet());
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, SuplaDataPacket msg, ByteBuf out) {
        if (NOISY_CALL_TYPE_IDS.contains(msg.callId())) {
            // log pings in trace
            log.trace(
                    "SuplaDataPacketEncoder.encode(ctx, {}, out)"
                            + " (SUPLA_SDC_CALL_PING_SERVER_RESULT)",
                    msg);
        } else {
            log.debug("SuplaDataPacketEncoder.encode(ctx, {}, out)", msg);
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
