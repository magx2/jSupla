package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.ds.FirmwareUpdateParamsEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.FirmwareUpdateParams;

import static java.util.Objects.requireNonNull;

public final class FirmwareUpdateParamsEncoderImpl implements FirmwareUpdateParamsEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public FirmwareUpdateParamsEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(FirmwareUpdateParams proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.platform, data, offset);
        offset += primitiveEncoder.writeInteger(proto.param1, data, offset);
        offset += primitiveEncoder.writeInteger(proto.param2, data, offset);
        offset += primitiveEncoder.writeInteger(proto.param3, data, offset);
        offset += primitiveEncoder.writeInteger(proto.param4, data, offset);

        return data;
    }
}
