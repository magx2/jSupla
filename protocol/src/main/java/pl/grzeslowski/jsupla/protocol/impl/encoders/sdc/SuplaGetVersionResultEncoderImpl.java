package pl.grzeslowski.jsupla.protocol.impl.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaGetVersionResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;

import static java.util.Objects.requireNonNull;

public final class SuplaGetVersionResultEncoderImpl implements SuplaGetVersionResultEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaGetVersionResultEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaGetVersionResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.protoVersionMin, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.protoVersion, data, offset);
        offset += primitiveEncoder.writeBytes(proto.softVer, data, offset);

        return data;
    }
}
