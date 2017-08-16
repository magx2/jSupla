package pl.grzeslowski.jsupla.protocol.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaRegisterClient;

import static java.util.Objects.requireNonNull;

@Deprecated
public final class SuplaRegisterClientEncoder implements ClientServerEncoder<SuplaRegisterClient> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaRegisterClientEncoder(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterClient proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.accessId, data, offset);
        offset += primitiveEncoder.writeBytes(proto.accessIdPwd, data, offset);
        offset += primitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += primitiveEncoder.writeBytes(proto.name, data, offset);
        offset += primitiveEncoder.writeBytes(proto.softVer, data, offset);

        return data;
    }
}
