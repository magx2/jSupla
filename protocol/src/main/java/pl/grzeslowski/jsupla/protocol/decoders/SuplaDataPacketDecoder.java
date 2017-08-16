package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaDataPacketDecoder implements Decoder<SuplaDataPacket> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaDataPacketDecoder(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaDataPacket decode(byte[] bytes, int offset) {
        offset += ProtoConsts.SUPLA_TAG_SIZE;

        final short version = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final long rrId = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final long callType = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final long dataSize = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] data = Arrays.copyOfRange(bytes, offset, offset + (int) dataSize);

        return new SuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
