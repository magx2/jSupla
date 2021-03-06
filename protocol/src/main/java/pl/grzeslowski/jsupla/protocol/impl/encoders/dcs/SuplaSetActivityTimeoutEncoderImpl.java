package pl.grzeslowski.jsupla.protocol.impl.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.dcs.SuplaSetActivityTimeoutEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaSetActivityTimeoutEncoderImpl implements SuplaSetActivityTimeoutEncoder {
    public static final SuplaSetActivityTimeoutEncoderImpl INSTANCE =
            new SuplaSetActivityTimeoutEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    SuplaSetActivityTimeoutEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @Override
    public byte[] encode(SuplaSetActivityTimeout proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        primitiveEncoder.writeUnsignedByte(proto.activityTimeout, data, offset);

        return data;
    }
}
