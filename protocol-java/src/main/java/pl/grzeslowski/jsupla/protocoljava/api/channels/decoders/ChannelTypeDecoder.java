package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;

public interface ChannelTypeDecoder {
    ChannelValue decode(final ChannelType channelType, byte[] value);
}
