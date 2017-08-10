package pl.grzeslowski.jsupla.protocol.decoders.dcs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.SuplaSetActivityTimeout;

public final class SuplaSetActivityTimeoutDecoder implements DeviceClientServerDecoder<SuplaSetActivityTimeout> {
    @Override
    public SuplaSetActivityTimeout decode(byte[] bytes, int offset) {
        final short activityTimeout = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        return new SuplaSetActivityTimeout(activityTimeout);
    }
}
