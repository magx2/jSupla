package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelNewValueToChannelType;

@Deprecated
public class SuplaChannelNewValueToChannelTypeImpl implements SuplaChannelNewValueToChannelType {
    @Override
    public ChannelType toChannelType(final SuplaChannelNewValue suplaChannelNewValue) {
        throw new UnsupportedOperationException("SuplaChannelNewValueToChannelTypeImpl.toChannelType(suplaChannelNewValue)");//TODO
    }
}
