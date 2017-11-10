package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;
import pl.grzeslowski.jsupla.protocoljava.common.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;
import static pl.grzeslowski.jsupla.protocoljava.common.TestUtil.lowerFirstLetter;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public abstract class SerializerFactoryTest<T extends Entity> {
    final T entity;
    final String serializerName;

    protected SerializerFactoryTest(final Class<T> entityClass) {
        this.entity = RANDOM_ENTITY.nextObject(entityClass);
        serializerName = lowerFirstLetter(entityClass.getSimpleName() + "Serializer");
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFIndSerializerForEntity() throws Exception {

        // given
        final Object givenSerializer = TestUtil.getDeclaredField(this.getClass(), serializerName).get(this);

        // when
        final Serializer<?, ? extends Proto> serializer = getFactory().getSerializer(entity);

        // then
        assertThat(serializer).isEqualTo(givenSerializer);
    }

    protected abstract SerializerFactory<? super T, ? extends Proto> getFactory();

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPassedProtoIsNull() {
        getFactory().getSerializer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenProtoTypeIsUnknown() throws Exception {
        getFactory().getSerializer(mock(superInterface()));
    }

    protected abstract Class<T> superInterface();
}
