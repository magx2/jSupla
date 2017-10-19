package pl.grzeslowski.jsupla.protocoljava.api.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.PercentValue;

public interface PercentTypeChannelEncoder {
    byte[] encode(PercentValue percentValue);
}
