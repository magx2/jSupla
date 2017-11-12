package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class FirmwareUpdateUrlResultParserImplTest
        extends AbstractParserTest<FirmwareUpdateUrlResult, SuplaFirmwareUpdateUrlResult> {
    @InjectMocks FirmwareUpdateUrlResultParserImpl parser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;

    @Override
    protected SuplaFirmwareUpdateUrlResult given() {
        final SuplaFirmwareUpdateUrlResult supla = super.given();
        BDDMockito.given(firmwareUpdateUrlParser.parse(supla.url))
                .willReturn(RANDOM_ENTITY.nextObject(FirmwareUpdateUrl.class));
        return supla;
    }

    @Override
    protected void then(final FirmwareUpdateUrlResult entity, final SuplaFirmwareUpdateUrlResult supla) {
        assertThat(entity.isExists()).isEqualTo(supla.exists != 0);
        verify(firmwareUpdateUrlParser).parse(supla.url);
    }

    @Override
    protected Parser<FirmwareUpdateUrlResult, SuplaFirmwareUpdateUrlResult> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaFirmwareUpdateUrlResult> classToTest() {
        return SuplaFirmwareUpdateUrlResult.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenFirmwareUpdateUrlParserIsNull() {
        new FirmwareUpdateUrlResultParserImpl(null);
    }
}