package pl.grzeslowski.jsupla.protocoljava.api.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;

public interface DeviceChannelValueSerializer
    extends DeviceServerSerializer<DeviceChannelValue, SuplaDeviceChannelValue> {
}
