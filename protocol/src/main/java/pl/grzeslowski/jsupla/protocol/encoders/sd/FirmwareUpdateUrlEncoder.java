package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrl;

public final class FirmwareUpdateUrlEncoder implements ServerDeviceEncoder<FirmwareUpdateUrl> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(FirmwareUpdateUrl proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeByte(proto.availableProtocols, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.host, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.port, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.path, data, offset);

        return data;
    }
}
