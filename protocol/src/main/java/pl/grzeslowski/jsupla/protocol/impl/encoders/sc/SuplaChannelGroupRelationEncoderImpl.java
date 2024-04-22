package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupRelationEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelGroupRelationEncoderImpl implements SuplaChannelGroupRelationEncoder {
    public static final SuplaChannelGroupRelationEncoderImpl INSTANCE =
        new SuplaChannelGroupRelationEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    SuplaChannelGroupRelationEncoderImpl(final PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaChannelGroupRelation proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.channelGroupId, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.channelId, bytes, offset);

        return bytes;
    }
}
