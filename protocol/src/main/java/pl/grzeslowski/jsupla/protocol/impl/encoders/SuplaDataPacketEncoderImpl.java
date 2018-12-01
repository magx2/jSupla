package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

import static java.util.Objects.requireNonNull;

public final class SuplaDataPacketEncoderImpl implements SuplaDataPacketEncoder {
    public static final SuplaDataPacketEncoderImpl INSTANCE =
            new SuplaDataPacketEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    SuplaDataPacketEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaDataPacket proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.version, data, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.rrId, data, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.callType, data, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.dataSize, data, offset);
        offset += primitiveEncoder.writeBytes(proto.data, data, offset);

        return data;
    }
}
