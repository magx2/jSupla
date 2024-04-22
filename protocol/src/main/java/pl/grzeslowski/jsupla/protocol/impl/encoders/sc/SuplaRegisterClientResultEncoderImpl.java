package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaRegisterClientResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterClientResultEncoderImpl implements SuplaRegisterClientResultEncoder {
    public static final SuplaRegisterClientResultEncoderImpl INSTANCE =
        new SuplaRegisterClientResultEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    SuplaRegisterClientResultEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterClientResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.resultCode, data, offset);
        offset += primitiveEncoder.writeInteger(proto.clientId, data, offset);
        offset += primitiveEncoder.writeInteger(proto.locationCount, data, offset);
        offset += primitiveEncoder.writeInteger(proto.channelCount, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.activityTimeout, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.version, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.versionMin, data, offset);

        return data;
    }
}
