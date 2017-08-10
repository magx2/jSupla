package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaEvent;

public class SuplaEventEncoder implements ServerClientEncoder<SuplaEvent> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaEvent proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.event, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.channelId, data, offset);
        offset += PrimitiveEncoder.writeUnsignedInteger(proto.durationMs, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.senderId, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.senderNameSize, data, offset);
        offset += PrimitiveEncoder.writeBytes(proto.senderName, data, offset);

        return data;
    }
}
