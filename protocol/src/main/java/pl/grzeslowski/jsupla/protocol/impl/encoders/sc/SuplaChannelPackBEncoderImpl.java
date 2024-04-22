package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelPackBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelPackBEncoderImpl implements SuplaChannelPackBEncoder {
    public static final SuplaChannelPackBEncoderImpl INSTANCE =
        new SuplaChannelPackBEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaChannelBEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelBEncoder suplaChannelBEncoder;

    SuplaChannelPackBEncoderImpl(final PrimitiveEncoder primitiveEncoder,
                                 final SuplaChannelBEncoder suplaChannelBEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.suplaChannelBEncoder = requireNonNull(suplaChannelBEncoder);
    }

    @Override
    public byte[] encode(final SuplaChannelPackB proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.count, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.totalLeft, bytes, offset);
        for (SuplaChannelB channel : proto.channels) {
            final byte[] chanellBytes = suplaChannelBEncoder.encode(channel);
            offset += primitiveEncoder.writeBytes(chanellBytes, bytes, offset);
        }

        return bytes;
    }
}
