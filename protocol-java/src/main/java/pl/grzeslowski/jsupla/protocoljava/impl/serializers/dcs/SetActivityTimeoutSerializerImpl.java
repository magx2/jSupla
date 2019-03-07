package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.RegisterClientSerializerImpl;

import javax.validation.constraints.NotNull;

public class SetActivityTimeoutSerializerImpl implements SetActivityTimeoutSerializer {
    public static final SetActivityTimeoutSerializerImpl INSTANCE = new SetActivityTimeoutSerializerImpl();

    SetActivityTimeoutSerializerImpl() {
    }

    @Override
    public SuplaSetActivityTimeout serialize(@NotNull final SetActivityTimeout entity) {
        return new SuplaSetActivityTimeout((short) entity.getActivityTimeout());
    }
}
