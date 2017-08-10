package pl.grzeslowski.jsupla.protocol.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValueB;

public class SuplaChannelNewValueBEncoder implements ClientServerEncoder<SuplaChannelNewValueB> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValueB proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.channelId, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
