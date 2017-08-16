package pl.grzeslowski.jsupla.protocol.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValue;

import static java.util.Objects.requireNonNull;

@Deprecated
public final class SuplaChannelNewValueEncoder implements ClientServerEncoder<SuplaChannelNewValue> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaChannelNewValueEncoder(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.channelId, data, offset);
        offset += primitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
