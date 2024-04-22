package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class TimevalSerializerImplTest extends SerializerTest<Timeval, SuplaTimeval> {
    @InjectMocks
    TimevalSerializerImpl serializer;

    @Override
    protected void then(final Timeval entity, final SuplaTimeval proto) {
        assertThat(proto.seconds).isEqualTo(entity.getSeconds());
        assertThat(proto.milliseconds).isEqualTo(entity.getMilliseconds());
    }

    @Override
    protected Serializer<Timeval, SuplaTimeval> serializer() {
        return serializer;
    }

    @Override
    protected Class<Timeval> entityClass() {
        return Timeval.class;
    }
}