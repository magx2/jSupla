package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue;

public final class SuplaChannelValueEncoder implements Encoder<SuplaChannelValue> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeBytes(proto.value, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.subValue, data, offset);

        return data;
    }
}
