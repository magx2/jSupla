package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;

public interface SdSuplaChannelNewValueToChannelType {
    ChannelType toChannelType(SuplaChannelNewValue suplaChannelNewValue);
}
