package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;

public interface ColorTypeChannelDecoder extends Decoder<ChannelValue> {
    @Override
    RgbValue decode(byte[] bytes, int offset);

    @Override
    default RgbValue decode(byte[] bytes) {
        return decode(bytes, 0);
    }
}
