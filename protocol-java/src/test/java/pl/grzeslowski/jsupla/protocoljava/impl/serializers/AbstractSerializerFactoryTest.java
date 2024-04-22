package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;
import static pl.grzeslowski.jsupla.protocoljava.common.TestUtil.getDeclaredField;
import static pl.grzeslowski.jsupla.protocoljava.common.TestUtil.lowerFirstLetter;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public abstract class AbstractSerializerFactoryTest<T extends Entity> {
    final T entity;
    final String serializerName;

    protected AbstractSerializerFactoryTest(final Class<T> entityClass) {
        this.entity = RANDOM_ENTITY.nextObject(entityClass);
        serializerName = lowerFirstLetter(entityClass.getSimpleName() + "Serializer");
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldFIndSerializerForEntity() throws Exception {

        // given
        final Serializer<T, ?> givenSerializer = (Serializer<T, ?>) getDeclaredField(this.getClass(), serializerName)
            .get(this);

        // when
        getSerializer().serialize(entity);

        // then
        verify(givenSerializer).serialize(entity);
    }

    protected abstract Serializer<T, ?> getSerializer();

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPassedProtoIsNull() {
        getSerializer().serialize(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenProtoTypeIsUnknown() throws Exception {
        getSerializer().serialize(mock(superInterface()));
    }

    protected abstract Class<T> superInterface();
}
