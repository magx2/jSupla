package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.SetActivityTimeoutResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

public class SetActivityTimeoutResultSerializerImpl implements SetActivityTimeoutResultSerializer {
    public static final SetActivityTimeoutResultSerializerImpl INSTANCE = new SetActivityTimeoutResultSerializerImpl();

    @SuppressWarnings("WeakerAccess")
    SetActivityTimeoutResultSerializerImpl() {
    }

    @Override
    public SuplaSetActivityTimeoutResult serialize(@NotNull final SetActivityTimeoutResult entity) {
        return new SuplaSetActivityTimeoutResult(
                                                        (short) entity.getActivityTimeout(),
                                                        (short) entity.getMin(),
                                                        (short) entity.getMax()
        );
    }
}
