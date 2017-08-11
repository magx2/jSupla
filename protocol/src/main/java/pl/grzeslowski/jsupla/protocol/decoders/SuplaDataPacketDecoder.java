package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaDataPacketDecoder implements Decoder<SuplaDataPacket> {
    @Override
    public SuplaDataPacket decode(byte[] bytes, int offset) {
        offset += ProtoConsts.SUPLA_TAG_SIZE;

        final short version = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final long rrId = PrimitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final long callType = PrimitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final long dataSize = PrimitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] data = Arrays.copyOfRange(bytes, offset, offset + (int) dataSize);


        return new SuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
