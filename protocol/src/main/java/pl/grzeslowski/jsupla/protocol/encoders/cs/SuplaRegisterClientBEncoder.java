package pl.grzeslowski.jsupla.protocol.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaRegisterClientB;

public final class SuplaRegisterClientBEncoder implements ClientServerEncoder<SuplaRegisterClientB> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterClientB proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.accessId, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.accessIdPwd, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.guid, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.name, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.softVer, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.serverName, data, offset);

        return data;
    }
}
