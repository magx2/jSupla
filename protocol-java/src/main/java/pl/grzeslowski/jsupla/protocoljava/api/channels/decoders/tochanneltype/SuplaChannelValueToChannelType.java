package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;

public interface SuplaChannelValueToChannelType {
    ChannelType toChannelType(SuplaChannelValue suplaChannelValue);

    ChannelType toChannelTypeSubValue(SuplaChannelValue suplaChannelValue);
}
