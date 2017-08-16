package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaEventEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;

import static java.util.Objects.requireNonNull;

public final class SuplaEventEncoderImpl implements SuplaEventEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaEventEncoderImpl(PrimitiveEncoder primitiveEncoder) {
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
