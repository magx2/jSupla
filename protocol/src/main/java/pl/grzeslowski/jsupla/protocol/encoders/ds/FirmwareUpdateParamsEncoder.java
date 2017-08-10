package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.FirmwareUpdateParams;

public class FirmwareUpdateParamsEncoder implements DeviceServerEncoder<FirmwareUpdateParams> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(FirmwareUpdateParams proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeByte(proto.platform, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.param1, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.param2, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.param3, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.param4, data, offset);

        return data;
    }
}
