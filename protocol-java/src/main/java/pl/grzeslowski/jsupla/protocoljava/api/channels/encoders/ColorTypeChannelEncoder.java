package pl.grzeslowski.jsupla.protocoljava.api.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;

public interface ColorTypeChannelEncoder {
    byte[] encode(RgbValue rgbValue);
}
