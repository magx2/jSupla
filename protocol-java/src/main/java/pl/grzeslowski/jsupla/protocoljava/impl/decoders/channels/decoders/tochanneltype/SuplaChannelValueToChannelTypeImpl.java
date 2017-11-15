package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelValueToChannelType;

public class SuplaChannelValueToChannelTypeImpl implements SuplaChannelValueToChannelType {
    @Override
    public ChannelType toChannelType(final SuplaChannelValue suplaChannelValue) {
        throw new UnsupportedOperationException("SuplaChannelValueToChannelTypeImpl.toChannelType(suplaChannelValue)"); // TODO
    }

    @Override
    public ChannelType toChannelTypeSubValue(final SuplaChannelValue suplaChannelValue) {
        throw new UnsupportedOperationException("SuplaChannelValueToChannelTypeImpl.toChannelTypeSubValue(suplaChannelValue)"); // TODO
    }
}
