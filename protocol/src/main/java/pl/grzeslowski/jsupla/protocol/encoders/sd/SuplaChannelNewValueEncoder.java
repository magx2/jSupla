package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaChannelNewValue;

public final class SuplaChannelNewValueEncoder implements ServerDeviceEncoder<SuplaChannelNewValue> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.senderId, data, offset);
        offset += PrimitiveEncoder.writeUnsignedByte(proto.channelNumber, data, offset);
        offset += PrimitiveEncoder.writeUnsignedInteger(proto.durationMs, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
