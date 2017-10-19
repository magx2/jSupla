package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static java.util.Arrays.copyOfRange;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class RegisterClientBParserImplTest extends ParserTest<RegisterClientB, SuplaRegisterClientB> {
    @InjectMocks RegisterClientBParserImpl parser;
    @Mock StringParser stringParser;

    @Override
    protected SuplaRegisterClientB given() {
        final SuplaRegisterClientB supla = super.given();
        BDDMockito.given(stringParser.parsePassword(supla.accessIdPwd))
                .willReturn(copyOfRange(RANDOM_ENTITY.nextObject(char[].class), 0, 10));
        BDDMockito.given(stringParser.parse(any()))
                .willReturn(RANDOM_ENTITY.nextObject(String.class).substring(0, 5));
        return supla;
    }

    @Override
    protected void then(final RegisterClientB entity, final SuplaRegisterClientB supla) {
        assertThat(entity.getAccessId()).isEqualTo(supla.accessId);
        verify(stringParser).parsePassword(supla.accessIdPwd);
        verify(stringParser).parse(supla.guid);
        verify(stringParser).parse(supla.name);
        verify(stringParser).parse(supla.softVer);
        verify(stringParser).parse(supla.serverName);
    }

    @Override
    protected Parser<RegisterClientB, SuplaRegisterClientB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterClientB> classToTest() {
        return SuplaRegisterClientB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new RegisterClientBParserImpl(null);
    }
}