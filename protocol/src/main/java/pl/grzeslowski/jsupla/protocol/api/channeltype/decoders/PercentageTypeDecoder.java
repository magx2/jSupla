package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

public class PercentageTypeDecoder {
    public PercentValue decode(byte[] value) {
        var percent = PrimitiveDecoder.INSTANCE.parseUnsignedByte(value, 0);
        if (percent > 100 || percent < 0) {
            percent = 0;
        }
        return new PercentValue(percent);
    }
}
