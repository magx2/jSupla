package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class FirmwareUpdateParamsParserImplTest
    extends AbstractParserTest<FirmwareUpdateParams, SuplaFirmwareUpdateParams> {
    @InjectMocks
    FirmwareUpdateParamsParserImpl parser;

    @Override
    protected void then(final FirmwareUpdateParams entity, final SuplaFirmwareUpdateParams supla) {
        assertThat(entity.getPlatform()).isEqualTo((int) supla.platform);
        assertThat(entity.getParam1()).isEqualTo((int) supla.param1);
        assertThat(entity.getParam2()).isEqualTo((int) supla.param2);
        assertThat(entity.getParam3()).isEqualTo((int) supla.param3);
        assertThat(entity.getParam4()).isEqualTo((int) supla.param4);
    }

    @Override
    protected Parser<FirmwareUpdateParams, SuplaFirmwareUpdateParams> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaFirmwareUpdateParams> classToTest() {
        return SuplaFirmwareUpdateParams.class;
    }
}