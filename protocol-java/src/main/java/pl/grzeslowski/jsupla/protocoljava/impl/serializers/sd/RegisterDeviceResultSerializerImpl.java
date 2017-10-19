package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.RegisterDeviceResultSerializer;

import javax.validation.constraints.NotNull;

public class RegisterDeviceResultSerializerImpl implements RegisterDeviceResultSerializer {
    @Override
    public SuplaRegisterDeviceResult serialize(@NotNull final RegisterDeviceResult entity) {
        return new SuplaRegisterDeviceResult(
                                                    entity.getResultCode(),
                                                    (byte) entity.getActivityTimeout(),
                                                    (byte) entity.getVersion(),
                                                    (byte) entity.getVersionMin()
        );
    }
}
