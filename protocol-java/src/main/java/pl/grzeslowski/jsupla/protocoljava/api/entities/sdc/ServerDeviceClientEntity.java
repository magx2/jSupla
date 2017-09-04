package pl.grzeslowski.jsupla.protocoljava.api.entities.sdc;

import pl.grzeslowski.jsupla.protocoljava.api.types.FromServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.ToClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.ToDeviceEntity;

public interface ServerDeviceClientEntity extends FromServerEntity, ToDeviceEntity, ToClientEntity {
}
