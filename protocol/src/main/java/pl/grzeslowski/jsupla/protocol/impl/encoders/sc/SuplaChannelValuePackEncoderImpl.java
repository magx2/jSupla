package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelValuePackEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelValuePackEncoderImpl implements SuplaChannelValuePackEncoder {
    public static final SuplaChannelValuePackEncoderImpl INSTANCE =
        new SuplaChannelValuePackEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaChannelValueEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelValueEncoder suplaChannelValueEncoder;

    SuplaChannelValuePackEncoderImpl(final PrimitiveEncoder primitiveEncoder,
                                     final SuplaChannelValueEncoder suplaChannelValueEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.suplaChannelValueEncoder = requireNonNull(suplaChannelValueEncoder);
    }

    @Override
    public byte[] encode(final SuplaChannelValuePack proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.count, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.totalLeft, bytes, offset);
        for (SuplaChannelValue item : proto.items) {
            final byte[] itemBytes = suplaChannelValueEncoder.encode(item);
            offset += primitiveEncoder.writeBytes(itemBytes, bytes, offset);
        }

        return bytes;
    }
}
