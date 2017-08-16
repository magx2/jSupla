package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelValueEncoderImpl implements SuplaChannelValueEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaChannelValueEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeBytes(proto.value, data, offset);
        offset += primitiveEncoder.writeBytes(proto.subValue, data, offset);

        return data;
    }
}
