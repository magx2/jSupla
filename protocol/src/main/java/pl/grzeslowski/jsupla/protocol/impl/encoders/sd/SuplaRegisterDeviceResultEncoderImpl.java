package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.SuplaRegisterDeviceResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterDeviceResultEncoderImpl implements SuplaRegisterDeviceResultEncoder {
    public static final SuplaRegisterDeviceResultEncoderImpl INSTANCE =
        new SuplaRegisterDeviceResultEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    SuplaRegisterDeviceResultEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterDeviceResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.resultCode, data, offset);
        offset += primitiveEncoder.writeByte(proto.activityTimeout, data, offset);
        offset += primitiveEncoder.writeByte(proto.version, data, offset);
        offset += primitiveEncoder.writeByte(proto.versionMin, data, offset);

        return data;
    }
}
