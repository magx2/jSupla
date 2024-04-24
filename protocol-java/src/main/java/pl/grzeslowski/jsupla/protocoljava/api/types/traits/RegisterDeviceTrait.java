package pl.grzeslowski.jsupla.protocoljava.api.types.traits;

import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannels;

public interface RegisterDeviceTrait {
    String getGuid();

    String getName();

    DeviceChannels getChannels();

    String getSoftVer();
}
