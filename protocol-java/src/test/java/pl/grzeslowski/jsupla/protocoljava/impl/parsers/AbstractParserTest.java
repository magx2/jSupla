package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractParserTest<EntityT extends Entity, SuplaProtoT extends Proto> {

    @Test
    public void shouldParseEntity() throws Exception {

        // given
        final SuplaProtoT supla = given();

        // when
        final EntityT entity = parser().parse(supla);

        // then
        assertThat(entity).isNotNull();
        then(entity, supla);
    }

    protected SuplaProtoT given() {
        return RANDOM_SUPLA.nextObject(classToTest());
    }

    protected void givenStringParser(StringParser stringParser) {
        BDDMockito.given(stringParser.parse(any()))
                .willReturn(RANDOM_ENTITY.nextObject(String.class).substring(0, 5));
        BDDMockito.given(stringParser.parseHexString(any()))
                .willReturn(RANDOM_ENTITY.nextObject(String.class).substring(0, 5));
        BDDMockito.given(stringParser.parsePassword(any()))
                .willReturn(Arrays.copyOfRange(RANDOM_ENTITY.nextObject(char[].class), 0, 5));
    }

    protected abstract void then(final EntityT entity, final SuplaProtoT supla);

    protected abstract Parser<EntityT, SuplaProtoT> parser();

    protected abstract Class<SuplaProtoT> classToTest();
}
