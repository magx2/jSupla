package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static java.util.Objects.requireNonNull;

public class TimevalEncoderImpl implements TimevalEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public TimevalEncoderImpl(final PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaTimeval proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.seconds, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.milliseconds, bytes, offset);

        return bytes;
    }
}
