package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SdSuplaChannelNewValueToChannelType;

public class SdSuplaChannelNewValueToChannelTypeImpl implements SdSuplaChannelNewValueToChannelType {
    @Override
    public ChannelType toChannelType(final SuplaChannelNewValue suplaChannelNewValue) {
        throw new UnsupportedOperationException("SdSuplaChannelNewValueToChannelTypeImpl.toChannelType(suplaChannelNewValue)");//TODO
    }
}
