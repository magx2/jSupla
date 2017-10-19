package pl.grzeslowski.jsupla.protocoljava.api.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OnOff;

public interface RelayTypeChannelEncoder {
    byte[] encode(OnOff onOff);
}
