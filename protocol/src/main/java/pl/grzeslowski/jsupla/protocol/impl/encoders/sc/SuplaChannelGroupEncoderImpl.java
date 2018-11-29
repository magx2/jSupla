package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelGroupEncoderImpl implements SuplaChannelGroupEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaChannelGroupEncoderImpl(final PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaChannelGroup proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.id, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.locationId, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.func, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.altIcon, bytes, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.flags, bytes, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.captionSize, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.caption, bytes, offset);

        return bytes;
    }
}
