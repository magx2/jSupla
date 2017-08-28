package pl.grzeslowski.jsupla.protocol.api.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channelvalues.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

public interface ColorTypeChannelDecoder extends Decoder<ChannelValue> {
    @Override
    RgbValue decode(byte[] bytes, int offset);

    @Override
    default RgbValue decode(byte[] bytes) {
        return decode(bytes, 0);
    }
}
