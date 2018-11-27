package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaNewValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;

import static java.util.Objects.requireNonNull;

public final class SuplaNewValueEncoderImpl implements SuplaNewValueEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaNewValueEncoderImpl(final PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @Override
    public byte[] encode(final SuplaNewValue proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.id, bytes, offset);
        offset += primitiveEncoder.writeByte(proto.target, bytes, offset);
        primitiveEncoder.writeBytes(proto.value, bytes, offset);

        return bytes;
    }
}
