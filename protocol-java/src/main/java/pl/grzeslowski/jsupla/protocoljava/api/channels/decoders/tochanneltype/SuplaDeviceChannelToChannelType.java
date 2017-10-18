package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;

@Deprecated
public interface SuplaDeviceChannelToChannelType {
    ChannelType toChannelType(SuplaDeviceChannel suplaDeviceChannel);
}
