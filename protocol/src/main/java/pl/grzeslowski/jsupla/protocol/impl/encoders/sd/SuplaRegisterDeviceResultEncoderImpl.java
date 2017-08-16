package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sd.SuplaRegisterDeviceResultEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterDeviceResultEncoderImpl implements SuplaRegisterDeviceResultEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaRegisterDeviceResultEncoderImpl(PrimitiveEncoder primitiveEncoder) {
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
