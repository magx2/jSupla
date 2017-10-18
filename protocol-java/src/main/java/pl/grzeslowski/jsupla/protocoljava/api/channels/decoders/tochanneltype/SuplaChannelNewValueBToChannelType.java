package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;

public interface SuplaChannelNewValueBToChannelType {
    ChannelType toChannelType(SuplaChannelNewValueB suplaChannelNewValueB);
}
