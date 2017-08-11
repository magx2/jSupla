package pl.grzeslowski.jsupla.protocol.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaVersionError;

public final class SuplaVersionErrorEncoder implements ServerClientDeviceEncoder<SuplaVersionError> {
    @Override
    public byte[] encode(SuplaVersionError proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeUnsignedByte(proto.serverVersionMin, data, offset);
        offset += PrimitiveEncoder.writeUnsignedByte(proto.serverVersion, data, offset);

        return data;
    }
}