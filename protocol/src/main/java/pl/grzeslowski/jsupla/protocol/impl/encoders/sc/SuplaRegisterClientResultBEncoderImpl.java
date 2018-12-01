package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaRegisterClientResultBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterClientResultBEncoderImpl implements SuplaRegisterClientResultBEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaRegisterClientResultBEncoderImpl(final PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaRegisterClientResultB proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.resultCode, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.clientId, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.locationCount, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.channelCount, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.flags, bytes, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.activityTimeout, bytes, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.version, bytes, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.versionMin, bytes, offset);

        return bytes;
    }
}
