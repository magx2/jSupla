package pl.grzeslowski.jsupla.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import java.util.List;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_TAG;
import static pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket.MIN_SIZE;

final class SuplaDataPacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < MIN_SIZE + SUPLA_TAG.length) {
            return;
        }

        in.markReaderIndex();
        moveThoughtSuplaTag(in);
        final TSuplaDataPacket suplaDataPacket = readTSuplaDataPacket(in);

        out.add(suplaDataPacket);
    }

    private void moveThoughtSuplaTag(ByteBuf in) {
        for (int charPosition = 0; charPosition < SUPLA_TAG.length; charPosition++) {
            final char tagChar = in.readChar();
            if (tagChar != SUPLA_TAG[charPosition]) {
                in.resetReaderIndex();
                throw new CorruptedFrameException(format("Read char at position %s wsa '%s' but should be '%s'!", charPosition, tagChar, SUPLA_TAG[charPosition]));
            }
        }
    }

    private TSuplaDataPacket readTSuplaDataPacket(ByteBuf in) {
        short version = in.readUnsignedByte();
        long rrId = in.readUnsignedIntLE();
        long callType = in.readUnsignedIntLE();
        long dataSize = in.readUnsignedIntLE();
        byte[] data = new byte[Math.toIntExact(dataSize)];
        in.readBytes(data);

        return new TSuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
