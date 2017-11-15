package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelValueToChannelType;

public class SuplaDeviceChannelValueToChannelTypeImpl implements SuplaDeviceChannelValueToChannelType {
    @Override
    public ChannelType toChannelType(final SuplaDeviceChannelValue suplaDeviceChannelValue) {
        throw new UnsupportedOperationException("SuplaDeviceChannelValueToChannelTypeImpl.toChannelType(suplaDeviceChannelValue)");//TODO
    }
}
