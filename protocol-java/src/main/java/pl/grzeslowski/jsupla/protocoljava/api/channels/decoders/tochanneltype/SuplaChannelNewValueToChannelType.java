package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;

@Deprecated
public interface SuplaChannelNewValueToChannelType {
    ChannelType toChannelType(SuplaChannelNewValue suplaChannelNewValue);
}
