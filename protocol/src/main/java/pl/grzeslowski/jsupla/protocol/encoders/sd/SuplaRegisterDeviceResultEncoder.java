package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;

import static pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder.writeByte;
import static pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder.writeInteger;

public final class SuplaRegisterDeviceResultEncoder implements ServerDeviceEncoder<SuplaRegisterDeviceResult> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterDeviceResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += writeInteger(proto.resultCode, data, offset);
        offset += writeByte(proto.activityTimeout, data, offset);
        offset += writeByte(proto.version, data, offset);
        offset += writeByte(proto.versionMin, data, offset);

        return data;
    }
}
