package pl.grzeslowski.jsupla.protocol.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValue;

@Deprecated
public class SuplaChannelNewValueEncoder implements ClientServerEncoder<SuplaChannelNewValue> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeByte(proto.channelId, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
