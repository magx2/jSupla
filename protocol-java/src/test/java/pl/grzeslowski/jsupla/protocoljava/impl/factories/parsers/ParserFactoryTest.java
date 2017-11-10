package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;
import pl.grzeslowski.jsupla.protocoljava.common.TestUtil;

import java.lang.reflect.Field;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public abstract class ParserFactoryTest<EntityT extends Entity, SuplaProtoT extends Proto> {
    final Class<SuplaProtoT> protoToTestClass;
    final Field resultField;

    protected ParserFactoryTest(final Class<SuplaProtoT> protoToTestClass, final Field resultField) {
        this.protoToTestClass = protoToTestClass;
        this.resultField = resultField;
    }

    @Before
    public final void init() {
        MockitoAnnotations.initMocks(this);
        resultField.setAccessible(true);
    }

    @Test
    public void shouldFindParser() throws Exception {

        // given
        final SuplaProtoT proto = RANDOM_SUPLA.nextObject(protoToTestClass);

        // when
        final Parser<? extends EntityT, ? extends SuplaProtoT> parser = parserFactory().getParser(proto);

        // then
        assertThat(parser).isEqualTo(resultField.get(this));
    }

    protected abstract ParserFactory<EntityT, SuplaProtoT> parserFactory();

    /**
     * @deprecated use TestUtil.
     */
    @Deprecated
    protected static Field getDeclaredField(Class<?> clazz, String field) throws NoSuchFieldException {
        return TestUtil.getDeclaredField(clazz, field);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenProtoTypeIsUnknown() throws Exception {
        parserFactory().getParser(mock(suplaClass()));
    }

    protected abstract Class<SuplaProtoT> suplaClass();

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPassedProtoIsNull() {
        parserFactory().getParser(null);
    }
}
