package pl.grzeslowski.jsupla.protocol.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaGetVersionResult;

public final class SuplaGetVersionResultEncoder implements ServerClientDeviceEncoder<SuplaGetVersionResult> {
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