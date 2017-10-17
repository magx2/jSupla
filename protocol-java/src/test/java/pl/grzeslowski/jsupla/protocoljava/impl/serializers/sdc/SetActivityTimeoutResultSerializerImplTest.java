package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class SetActivityTimeoutResultSerializerImplTest
        extends SerializerTest<SetActivityTimeoutResult, SuplaSetActivityTimeoutResult> {
    @InjectMocks SetActivityTimeoutResultSerializerImpl serializer;

    @Override
    protected void then(final SetActivityTimeoutResult entity, final SuplaSetActivityTimeoutResult proto) {
        assertThat((int) proto.activityTimeout).isEqualTo(entity.getActivityTimeout());
        assertThat((int) proto.min).isEqualTo(entity.getMin());
        assertThat((int) proto.max).isEqualTo(entity.getMax());
    }

    @Override
    protected Serializer<SetActivityTimeoutResult, SuplaSetActivityTimeoutResult> serializer() {
        return serializer;
    }

    @Override
    protected Class<SetActivityTimeoutResult> entityClass() {
        return SetActivityTimeoutResult.class;
    }
}