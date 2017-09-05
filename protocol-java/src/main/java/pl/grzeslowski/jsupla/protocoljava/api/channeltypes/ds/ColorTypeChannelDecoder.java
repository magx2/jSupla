package pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.RgbValue;

public interface ColorTypeChannelDecoder extends Decoder<ChannelValue> {
    @Override
    RgbValue decode(byte[] bytes, int offset);

    @Override
    default RgbValue decode(byte[] bytes) {
        return decode(bytes, 0);
    }
}
