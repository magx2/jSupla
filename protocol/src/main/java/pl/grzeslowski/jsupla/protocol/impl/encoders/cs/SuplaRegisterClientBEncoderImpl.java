package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaRegisterClientBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterClientBEncoderImpl implements SuplaRegisterClientBEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaRegisterClientBEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterClientB proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.accessId, data, offset);
        offset += primitiveEncoder.writeBytes(proto.accessIdPwd, data, offset);
        offset += primitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += primitiveEncoder.writeBytes(proto.name, data, offset);
        offset += primitiveEncoder.writeBytes(proto.softVer, data, offset);
        offset += primitiveEncoder.writeBytes(proto.serverName, data, offset);

        return data;
    }
}
