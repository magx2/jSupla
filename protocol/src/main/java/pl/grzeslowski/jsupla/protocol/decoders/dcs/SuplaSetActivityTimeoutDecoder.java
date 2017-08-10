package pl.grzeslowski.jsupla.protocol.decoders.dcs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.dcs.SuplaSetActivityTimeout;

public class SuplaSetActivityTimeoutDecoder implements DeviceClientServerDecoder<SuplaSetActivityTimeout> {
    @Override
    public SuplaSetActivityTimeout decode(byte[] bytes, int offset) {
        final short activityTimeout = PrimitiveParser.parseUnsignedByte(bytes, offset);
        return new SuplaSetActivityTimeout(activityTimeout);
    }
}
