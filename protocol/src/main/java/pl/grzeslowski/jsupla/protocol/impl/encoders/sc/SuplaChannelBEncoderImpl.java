package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.SuplaChannelValueEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelBEncoderImpl implements SuplaChannelBEncoder {
    public static final SuplaChannelBEncoderImpl INSTANCE =
            new SuplaChannelBEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaChannelValueEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelValueEncoder suplaChannelValueEncoder;

    SuplaChannelBEncoderImpl(final PrimitiveEncoder primitiveEncoder, 
                             final SuplaChannelValueEncoder suplaChannelValueEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.suplaChannelValueEncoder = requireNonNull(suplaChannelValueEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(final SuplaChannelB proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.id, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.locationId, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.func, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.altIcon, bytes, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.flags, bytes, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.protocolVersion, bytes, offset);
        offset += primitiveEncoder.writeByte(proto.online, bytes, offset);
        final byte[] valueBytes = suplaChannelValueEncoder.encode(proto.value);
        offset += primitiveEncoder.writeBytes(valueBytes, bytes, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.captionSize, bytes, offset);
        offset += primitiveEncoder.writeBytes(proto.caption, bytes, offset);

        return bytes;
    }
}
