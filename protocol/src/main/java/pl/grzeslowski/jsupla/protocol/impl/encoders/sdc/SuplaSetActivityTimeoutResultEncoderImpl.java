package pl.grzeslowski.jsupla.protocol.impl.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sdc.ServerClientDeviceEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaSetActivityTimeoutResult;

import static java.util.Objects.requireNonNull;

public final class SuplaSetActivityTimeoutResultEncoderImpl
        implements ServerClientDeviceEncoder<SuplaSetActivityTimeoutResult> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaSetActivityTimeoutResultEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaSetActivityTimeoutResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.activityTimeout, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.min, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.max, data, offset);

        return data;
    }
}