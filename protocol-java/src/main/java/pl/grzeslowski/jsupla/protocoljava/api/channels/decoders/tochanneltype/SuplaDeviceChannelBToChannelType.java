package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;

public interface SuplaDeviceChannelBToChannelType {
    ChannelType toChannelType(SuplaDeviceChannelB suplaDeviceChannelB);
}
