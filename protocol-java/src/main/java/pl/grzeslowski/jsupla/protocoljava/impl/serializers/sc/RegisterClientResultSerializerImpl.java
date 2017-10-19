package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultSerializer;

import javax.validation.constraints.NotNull;

public class RegisterClientResultSerializerImpl implements RegisterClientResultSerializer {
    @Override
    public SuplaRegisterClientResult serialize(@NotNull final RegisterClientResult entity) {
        return new SuplaRegisterClientResult(
                                                    entity.getResultCode(),
                                                    entity.getClientId(),
                                                    entity.getLocationCount(),
                                                    entity.getChannelCount(),
                                                    (short) entity.getActivityTimeout(),
                                                    (short) entity.getVersion(),
                                                    (short) entity.getVersionMin()
        );
    }
}
