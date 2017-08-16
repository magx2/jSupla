package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.cs.ClientServerEncoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValueB;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelNewValueBEncoderImpl implements ClientServerEncoder<SuplaChannelNewValueB> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaChannelNewValueBEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValueB proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.channelId, data, offset);
        offset += primitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
