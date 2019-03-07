package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ChannelValueParserImpl;

public class SuplaChannelValueToChannelTypeImpl implements SuplaChannelValueToChannelType {
    public static final SuplaChannelValueToChannelTypeImpl INSTANCE = new SuplaChannelValueToChannelTypeImpl();

    @SuppressWarnings("WeakerAccess")
    SuplaChannelValueToChannelTypeImpl() {
    }

    @Override
    public ChannelType toChannelType(final SuplaChannelValue suplaChannelValue) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public ChannelType toChannelTypeSubValue(final SuplaChannelValue suplaChannelValue) {
        throw new UnsupportedOperationException(); // TODO
    }
}
