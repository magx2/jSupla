package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;

public interface SuplaDeviceChannelValueToChannelType {
    ChannelType toChannelType(SuplaDeviceChannelValue suplaDeviceChannelValue);
}
