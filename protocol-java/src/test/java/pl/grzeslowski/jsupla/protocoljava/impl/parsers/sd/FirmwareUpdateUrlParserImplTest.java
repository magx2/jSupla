package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class FirmwareUpdateUrlParserImplTest extends ParserTest<FirmwareUpdateUrl, SuplaFirmwareUpdateUrl> {
    @InjectMocks FirmwareUpdateUrlParserImpl parser;
    @Mock StringParser stringParser;

    @Override
    protected SuplaFirmwareUpdateUrl given() {
        givenStringParser(stringParser);
        return super.given();
    }

    @Override
    protected void then(final FirmwareUpdateUrl entity, final SuplaFirmwareUpdateUrl supla) {
        assertThat(entity.getAvailableProtocols()).isEqualTo(supla.availableProtocols);
        verify(stringParser).parse(supla.host);
        assertThat(entity.getPort()).isEqualTo(supla.port);
        verify(stringParser).parse(supla.path);
    }

    @Override
    protected Parser<FirmwareUpdateUrl, SuplaFirmwareUpdateUrl> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaFirmwareUpdateUrl> classToTest() {
        return SuplaFirmwareUpdateUrl.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringPArserIsNull() {
        new FirmwareUpdateUrlParserImpl(null);
    }

}