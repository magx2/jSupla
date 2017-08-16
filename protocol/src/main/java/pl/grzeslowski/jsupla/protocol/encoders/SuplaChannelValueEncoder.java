package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelValueEncoder implements Encoder<SuplaChannelValue> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaChannelValueEncoder(PrimitiveEncoder primitiveEncoder) {
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
