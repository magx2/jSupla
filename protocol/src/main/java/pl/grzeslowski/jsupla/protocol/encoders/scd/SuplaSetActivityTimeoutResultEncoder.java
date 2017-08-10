package pl.grzeslowski.jsupla.protocol.encoders.scd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.scd.SuplaSetActivityTimeoutResult;

public class SuplaSetActivityTimeoutResultEncoder implements ServerClientDeviceEncoder<SuplaSetActivityTimeoutResult> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaSetActivityTimeoutResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeUnsignedByte(proto.activityTimeout, data, offset);
        offset += PrimitiveEncoder.writeUnsignedByte(proto.min, data, offset);
        offset += PrimitiveEncoder.writeUnsignedByte(proto.max, data, offset);

        return data;
    }
}
