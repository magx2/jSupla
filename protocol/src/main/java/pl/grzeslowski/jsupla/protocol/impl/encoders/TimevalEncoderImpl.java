package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;

import static java.util.Objects.requireNonNull;

public class TimevalEncoderImpl implements TimevalEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public TimevalEncoderImpl(final PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final Timeval proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeLong(proto.seconds, bytes, offset);
        offset += primitiveEncoder.writeLong(proto.milliseconds, bytes, offset);

        return bytes;
    }
}
