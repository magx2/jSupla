package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaRegisterClientCEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterClientCEncoderImpl implements SuplaRegisterClientCEncoder {
    public static final SuplaRegisterClientCEncoderImpl INSTANCE =
        new SuplaRegisterClientCEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    SuplaRegisterClientCEncoderImpl(final PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaRegisterClientC proto) {
        final byte[] bytes = new byte[proto.size()];

        int offset = 0;
        offset += primitiveEncoder.writeBytes(proto.email, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.authKey, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.guid, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.name, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.softVer, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.serverName, bytes, offset);

        return bytes;
    }
}
