package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;

import javax.validation.constraints.NotNull;

public class SetActivityTimeoutSerializerImpl implements SetActivityTimeoutSerializer {
    @Override
    public SuplaSetActivityTimeout serialize(@NotNull final SetActivityTimeout entity) {
        return new SuplaSetActivityTimeout((short) entity.getActivityTimeout());
    }
}
