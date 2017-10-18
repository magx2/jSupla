package pl.grzeslowski.jsupla.protocoljava.api.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;

public interface ChannelTypeEncoder {
    byte[] encode(ChannelValue channelValue);
}
