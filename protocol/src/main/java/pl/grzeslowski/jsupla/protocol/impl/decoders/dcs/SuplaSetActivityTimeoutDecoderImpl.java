package pl.grzeslowski.jsupla.protocol.impl.decoders.dcs;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaSetActivityTimeoutDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;

import static java.util.Objects.requireNonNull;

public final class SuplaSetActivityTimeoutDecoderImpl implements SuplaSetActivityTimeoutDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaSetActivityTimeoutDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaSetActivityTimeout decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaSetActivityTimeout.SIZE);
        final short activityTimeout = primitiveDecoder.parseUnsignedByte(bytes, offset);
        return new SuplaSetActivityTimeout(activityTimeout);
    }
}
