package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static java.util.Arrays.copyOfRange;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class RegisterClientParserImplTest extends AbstractParserTest<RegisterClient, SuplaRegisterClient> {
    @InjectMocks
    RegisterClientParserImpl parser;
    @Mock
    StringParser stringParser;

    @Override
    protected SuplaRegisterClient given() {
        final SuplaRegisterClient supla = super.given();
        BDDMockito.given(stringParser.parsePassword(supla.accessIdPwd))
            .willReturn(copyOfRange(RANDOM_ENTITY.nextObject(char[].class), 0, 10));
        BDDMockito.given(stringParser.parse(any()))
            .willReturn(RANDOM_ENTITY.nextObject(String.class).substring(0, 5));
        return supla;
    }

    @Override
    protected void then(final RegisterClient entity, final SuplaRegisterClient supla) {
        AssertionsForClassTypes.assertThat(entity.getAccessId()).isEqualTo(supla.accessId);
        verify(stringParser).parsePassword(supla.accessIdPwd);
        verify(stringParser).parse(supla.guid);
        verify(stringParser).parse(supla.name);
        verify(stringParser).parse(supla.softVer);
    }

    @Override
    protected Parser<RegisterClient, SuplaRegisterClient> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterClient> classToTest() {
        return SuplaRegisterClient.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new RegisterClientParserImpl(null);
    }
}
