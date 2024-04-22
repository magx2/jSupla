package pl.grzeslowski.jsupla.protocoljava.api.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;

public interface RegisterDeviceResultSerializer
    extends ServerDeviceSerializer<RegisterDeviceResult, SuplaRegisterDeviceResult> {
}
