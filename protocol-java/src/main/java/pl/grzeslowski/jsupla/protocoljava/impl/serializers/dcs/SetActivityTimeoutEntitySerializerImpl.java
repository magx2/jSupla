package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutEntitySerializer;

import javax.validation.constraints.NotNull;

public class SetActivityTimeoutEntitySerializerImpl implements SetActivityTimeoutEntitySerializer {
    @Override
    public SuplaSetActivityTimeout serialize(@NotNull final SetActivityTimeout entity) {
        return new SuplaSetActivityTimeout((short) entity.getActivityTimeout());
    }
}
