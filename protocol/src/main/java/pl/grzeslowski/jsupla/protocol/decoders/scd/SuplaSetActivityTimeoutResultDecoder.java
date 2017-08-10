package pl.grzeslowski.jsupla.protocol.decoders.scd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.scd.SuplaSetActivityTimeoutResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class SuplaSetActivityTimeoutResultDecoder implements ServerClientDeviceDecoder<SuplaSetActivityTimeoutResult> {
    @Override
    public SuplaSetActivityTimeoutResult decode(byte[] bytes, int offset) {
        final short activityTimeout = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short min = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short max = PrimitiveDecoder.parseUnsignedByte(bytes, offset);

        return new SuplaSetActivityTimeoutResult(activityTimeout, min, max);
    }
}
