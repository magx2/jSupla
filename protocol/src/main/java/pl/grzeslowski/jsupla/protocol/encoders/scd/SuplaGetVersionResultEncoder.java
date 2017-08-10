package pl.grzeslowski.jsupla.protocol.encoders.scd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.scd.SuplaGetVersionResult;

public class SuplaGetVersionResultEncoder implements ServerClientDeviceEncoder<SuplaGetVersionResult> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaGetVersionResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeUnsignedByte(proto.protoVersionMin, data, offset);
        offset += PrimitiveEncoder.writeUnsignedByte(proto.protoVersion, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.softVer, data, offset);

        return data;
    }
}
