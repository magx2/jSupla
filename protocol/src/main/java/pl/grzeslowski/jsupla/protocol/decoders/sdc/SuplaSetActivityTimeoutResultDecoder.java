package pl.grzeslowski.jsupla.protocol.decoders.sdc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaSetActivityTimeoutResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class SuplaSetActivityTimeoutResultDecoder
        implements ServerClientDeviceDecoder<SuplaSetActivityTimeoutResult> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaSetActivityTimeoutResultDecoder(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaSetActivityTimeoutResult decode(byte[] bytes, int offset) {
        final short activityTimeout = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short min = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short max = primitiveDecoder.parseUnsignedByte(bytes, offset);

        return new SuplaSetActivityTimeoutResult(activityTimeout, min, max);
    }
}
