package pl.grzeslowski.jsupla.protocoljava.api.entities.dcs;

import pl.grzeslowski.jsupla.protocoljava.api.types.FromClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.FromDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.ToServerEntity;

public interface DeviceClientServerEntity extends FromDeviceEntity, FromClientEntity, ToServerEntity {
}
