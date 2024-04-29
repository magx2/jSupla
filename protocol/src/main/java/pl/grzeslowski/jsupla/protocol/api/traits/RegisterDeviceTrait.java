package pl.grzeslowski.jsupla.protocol.api.traits;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;

import java.util.List;

public interface RegisterDeviceTrait {
    List<SuplaDeviceChannel> getChannels();
}
