package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;
import pl.grzeslowski.jsupla.protocoljava.common.TestUtil;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public abstract class ForInterfaceParserTest<EntityT extends Entity, SuplaProtoT extends Proto> {
    final Class<SuplaProtoT> protoToTestClass;
    final Field resultField;

    protected ForInterfaceParserTest(final Class<SuplaProtoT> protoToTestClass, final Field resultField) {
        this.protoToTestClass = protoToTestClass;
        this.resultField = resultField;
    }

    @Before
    public final void init() {
        MockitoAnnotations.initMocks(this);
        resultField.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldFindParser() throws Exception {

        // given
        final Parser<EntityT, SuplaProtoT> parser = (Parser<EntityT, SuplaProtoT>) resultField.get(this);
        final SuplaProtoT proto = RANDOM_SUPLA.nextObject(protoToTestClass);

        // when
        getParser().parse(proto);

        // then
        verify(parser).parse(proto);
    }

    protected abstract Parser<EntityT, SuplaProtoT> getParser();

    /**
     * @deprecated use TestUtil.
     */
    protected static Field getDeclaredField(Class<?> clazz, String field) throws NoSuchFieldException {
        return TestUtil.getDeclaredField(clazz, field);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenProtoTypeIsUnknown() {
        getParser().parse(mock(suplaClass()));
    }

    protected abstract Class<SuplaProtoT> suplaClass();

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPassedProtoIsNull() {
        getParser().parse(null);
    }
}
