package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

import static java.util.Objects.requireNonNull;

public final class SuplaDataPacketEncoderImpl implements Encoder<SuplaDataPacket> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaDataPacketEncoderImpl(PrimitiveEncoder primitiveEncoder) {
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