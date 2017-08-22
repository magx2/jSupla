package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaDataPacketDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaDataPacketDecoderImpl implements SuplaDataPacketDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaDataPacketDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaDataPacket decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes,
                offset + ProtoConsts.SUPLA_TAG_SIZE + SuplaDataPacket.MIN_SIZE);

        offset += ProtoConsts.SUPLA_TAG_SIZE;

        final short version = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final long rrId = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final long callType = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final long dataSize = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] data = primitiveDecoder.copyOfRange(bytes, offset, offset + (int) dataSize);

        return new SuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
