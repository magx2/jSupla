package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultBSerializer;

import javax.validation.constraints.NotNull;

public class RegisterClientResultBSerializerImpl implements RegisterClientResultBSerializer {
    @Override
    public SuplaRegisterClientResultB serialize(@NotNull RegisterClientResultB entity) {
        return new SuplaRegisterClientResultB(
                entity.getResultCode(),
                entity.getClientId(),
                entity.getLocationCount(),
                entity.getChannelCount(),
                entity.getFlags(),
                (short) entity.getActivityTimeout(),
                (short) entity.getVersion(),
                (short) entity.getVersionMin()
        );
    }
}
