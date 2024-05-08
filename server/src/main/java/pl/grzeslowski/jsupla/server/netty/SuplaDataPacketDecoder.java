package pl.grzeslowski.jsupla.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

import java.util.List;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

final class SuplaDataPacketDecoder extends ByteToMessageDecoder {
    public static final int SUPLA_DATA_PACKET_MIN_SIZE = BYTE_SIZE + INT_SIZE * 3;
    private final Logger logger = LoggerFactory.getLogger(SuplaDataPacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < SUPLA_DATA_PACKET_MIN_SIZE + SUPLA_TAG.length * 2) {
            return;
        }

        in.markReaderIndex();
        moveThoughtSuplaTag(in);
        final SuplaDataPacket suplaDataPacket = readTSuplaDataPacket(in);
        moveThoughtSuplaTag(in);

        logger.trace("Decoded {}", suplaDataPacket);
        out.add(suplaDataPacket);
    }

    private void moveThoughtSuplaTag(ByteBuf in) {
        for (int charPosition = 0; charPosition < SUPLA_TAG.length; charPosition++) {
            final byte tagChar = in.readByte();
            if (tagChar != SUPLA_TAG[charPosition]) {
                in.resetReaderIndex();
                throw new CorruptedFrameException(
                    format("Read char at position %s wsa '%s' but should be '%s'!",
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
