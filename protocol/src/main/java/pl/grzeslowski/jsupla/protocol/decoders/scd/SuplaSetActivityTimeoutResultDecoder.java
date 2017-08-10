package pl.grzeslowski.jsupla.protocol.decoders.scd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.scd.SuplaSetActivityTimeoutResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public class SuplaSetActivityTimeoutResultDecoder implements ServerClientDeviceDecoder<SuplaSetActivityTimeoutResult> {
    @Override
    public SuplaSetActivityTimeoutResult decode(byte[] bytes, int offset) {
        final short activityTimeout = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short min = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short max = PrimitiveParser.parseUnsignedByte(bytes, offset);

        return new SuplaSetActivityTimeoutResult(activityTimeout, min, max);
    }
}
