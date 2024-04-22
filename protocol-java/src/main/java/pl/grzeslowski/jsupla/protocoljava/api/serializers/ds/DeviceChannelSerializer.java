package pl.grzeslowski.jsupla.protocoljava.api.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

public interface DeviceChannelSerializer extends Serializer<DeviceChannel, SuplaDeviceChannel> {
}
