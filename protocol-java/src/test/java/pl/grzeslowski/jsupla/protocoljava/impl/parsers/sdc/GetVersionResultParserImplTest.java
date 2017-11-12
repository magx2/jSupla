package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class GetVersionResultParserImplTest extends AbstractParserTest<GetVersionResult, SuplaGetVersionResult> {
    @InjectMocks GetVersionResultParserImpl parser;
    @Mock StringParser stringParser;

    @Override
    protected SuplaGetVersionResult given() {
        givenStringParser(stringParser);
        return super.given();
    }

    @Override
    protected void then(final GetVersionResult entity, final SuplaGetVersionResult supla) {
        assertThat(entity.getProtoVersion()).isEqualTo(supla.protoVersion);
        assertThat(entity.getProtoVersionMin()).isEqualTo(supla.protoVersionMin);
        verify(stringParser).parse(supla.softVer);
    }

    @Override
    protected Parser<GetVersionResult, SuplaGetVersionResult> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaGetVersionResult> classToTest() {
        return SuplaGetVersionResult.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new GetVersionResultParserImpl(null);
    }
}