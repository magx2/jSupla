package pl.grzeslowski.jsupla.protocoljava.api.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;

public interface DeviceChannelValueParser
        extends DeviceServerParser<DeviceChannelValue, SuplaDeviceChannelValue> {
    interface TypeMapper {
        ChannelType findChannelType(int channelNumber);
    }
}
