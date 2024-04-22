package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class SetActivityTimeoutSerializerImplTest extends SerializerTest<SetActivityTimeout, SuplaSetActivityTimeout> {
    @InjectMocks
    SetActivityTimeoutSerializerImpl serializer;

    @Override
    protected void then(final SetActivityTimeout entity, final SuplaSetActivityTimeout proto) {
        assertThat((int) proto.activityTimeout).isEqualTo(entity.getActivityTimeout());
    }

    @Override
    protected Serializer<SetActivityTimeout, SuplaSetActivityTimeout> serializer() {
        return serializer;
    }

    @Override
    protected Class<SetActivityTimeout> entityClass() {
        return SetActivityTimeout.class;
    }
}