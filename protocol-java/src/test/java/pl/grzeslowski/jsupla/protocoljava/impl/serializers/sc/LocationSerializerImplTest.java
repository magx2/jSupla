package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class LocationSerializerImplTest extends SerializerTest<Location, SuplaLocation> {
    @InjectMocks LocationSerializerImpl serializer;
    @Mock StringSerializer stringSerializer;

    @Override
    protected Location given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @Override
    protected void then(final Location entity, final SuplaLocation proto) {
        assertThat((int) proto.eol).isEqualTo(entity.getEol());
        assertThat(proto.id).isEqualTo(entity.getId());
        assertThat(proto.captionSize).isEqualTo(entity.getCaption().length());
        verify(stringSerializer).serialize(entity.getCaption());
    }

    @Override
    protected Serializer<Location, SuplaLocation> serializer() {
        return serializer;
    }

    @Override
    protected Class<Location> entityClass() {
        return Location.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new LocationSerializerImpl(null);
    }
}