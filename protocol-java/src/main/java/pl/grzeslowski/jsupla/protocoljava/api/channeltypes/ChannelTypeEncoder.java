package pl.grzeslowski.jsupla.protocoljava.api.channeltypes;

import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

public interface ChannelTypeEncoder {
    byte[] encode(ChannelValue channelValue);
}
