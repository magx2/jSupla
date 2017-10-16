package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
public class LocationPackSerializerImplTest extends SerializerTest<LocationPack, SuplaLocationPack> {
    @InjectMocks LocationPackSerializerImpl serializer;
    @Mock LocationSerializer locationSerializer;

    @Override
    protected LocationPack given() {
        BDDMockito.given(locationSerializer.serialize(any())).willReturn(RANDOM_SUPLA.nextObject(SuplaLocation.class));
        return super.given();
    }

    @Override
    protected void then(final LocationPack entity, final SuplaLocationPack proto) {
        assertThat(proto.count).isEqualTo(entity.getLocations().size());
        assertThat(proto.totalLeft).isEqualTo(entity.getTotalLeft());
        entity.getLocations().forEach(channel -> verify(locationSerializer).serialize(channel));
        verifyNoMoreInteractions(locationSerializer);
    }

    @Override
    protected Serializer<LocationPack, SuplaLocationPack> serializer() {
        return serializer;
    }

    @Override
    protected Class<LocationPack> entityClass() {
        return LocationPack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenLocationSerializerIsNull() {
        new LocationPackSerializerImpl(null);
    }
}