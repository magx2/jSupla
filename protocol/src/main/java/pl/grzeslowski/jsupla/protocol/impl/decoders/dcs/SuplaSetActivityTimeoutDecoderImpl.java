package pl.grzeslowski.jsupla.protocol.impl.decoders.dcs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.dcs.DeviceClientServerDecoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.SuplaSetActivityTimeout;

import static java.util.Objects.requireNonNull;

public final class SuplaSetActivityTimeoutDecoderImpl implements DeviceClientServerDecoder<SuplaSetActivityTimeout> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaSetActivityTimeoutDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaSetActivityTimeout decode(byte[] bytes, int offset) {
        final short activityTimeout = primitiveDecoder.parseUnsignedByte(bytes, offset);
        return new SuplaSetActivityTimeout(activityTimeout);
    }
}
