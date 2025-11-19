package pl.grzeslowski.jsupla.server;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

final class SuplaDataPacketDecoder extends ByteToMessageDecoder {
    public static final int SUPLA_DATA_PACKET_MIN_SIZE = SuplaDataPacket.MIN_SIZE;
    private static final AtomicLong ID = new AtomicLong();
    private final Logger log;

    public SuplaDataPacketDecoder(String uuid) {
        log =
                LoggerFactory.getLogger(
                        SuplaDataPacketDecoder.class.getName()
                                + "#"
                                + uuid
                                + ":"
                                + ID.incrementAndGet());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < SUPLA_DATA_PACKET_MIN_SIZE + SUPLA_TAG.length * 2) {
            return;
        }

        in.markReaderIndex();
        moveThoughtSuplaTag(in);
        var suplaDataPacket = readTSuplaDataPacket(in);
        moveThoughtSuplaTag(in);

        if (suplaDataPacket.callId() == SUPLA_SDC_CALL_PING_SERVER_RESULT.getValue()) {
            // log pings in trace
            log.trace(
                    "SuplaDataPacketDecoder.decode {} (SUPLA_SDC_CALL_PING_SERVER_RESULT)",
                    suplaDataPacket);
        } else {
            log.debug("SuplaDataPacketDecoder.decode {}", suplaDataPacket);
        }
        out.add(suplaDataPacket);
    }

    private void moveThoughtSuplaTag(ByteBuf in) {
        for (int charPosition = 0; charPosition < SUPLA_TAG.length; charPosition++) {
            final byte tagChar = in.readByte();
            if (tagChar != SUPLA_TAG[charPosition]) {
                in.resetReaderIndex();
                throw new CorruptedFrameException(
                        format(
                                "Read char at position %s wsa '%s' but should be '%s'!",
                                charPosition, tagChar, SUPLA_TAG[charPosition]));
            }
        }
    }

    private SuplaDataPacket readTSuplaDataPacket(ByteBuf in) {
        short version = in.readUnsignedByte();
        long rrId = in.readUnsignedIntLE();
        long callType = in.readUnsignedIntLE();
        long dataSize = in.readUnsignedIntLE();
        byte[] data = new byte[Math.toIntExact(dataSize)];
        in.readBytes(data);

        return new SuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
