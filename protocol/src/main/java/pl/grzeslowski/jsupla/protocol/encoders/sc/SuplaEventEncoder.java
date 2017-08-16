package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaEvent;

import static java.util.Objects.requireNonNull;

public final class SuplaEventEncoder implements ServerClientEncoder<SuplaEvent> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaEventEncoder(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaEvent proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.event, data, offset);
        offset += primitiveEncoder.writeInteger(proto.channelId, data, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.durationMs, data, offset);
        offset += primitiveEncoder.writeInteger(proto.senderId, data, offset);
        offset += primitiveEncoder.writeInteger(proto.senderNameSize, data, offset);
        offset += primitiveEncoder.writeBytes(proto.senderName, data, offset);

        return data;
    }
}
