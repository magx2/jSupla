package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class RegisterDeviceResultParserImplTest
        extends AbstractParserTest<RegisterDeviceResult, SuplaRegisterDeviceResult> {
    @InjectMocks RegisterDeviceResultParserImpl parser;

    @Override
    protected void then(final RegisterDeviceResult entity, final SuplaRegisterDeviceResult supla) {
        assertThat(entity.getResultCode()).isEqualTo(supla.resultCode);
        assertThat(entity.getActivityTimeout()).isEqualTo(supla.activityTimeout);
        assertThat(entity.getVersion()).isEqualTo(supla.version);
        assertThat(entity.getVersionMin()).isEqualTo(supla.versionMin);
    }

    @Override
    protected Parser<RegisterDeviceResult, SuplaRegisterDeviceResult> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterDeviceResult> classToTest() {
        return SuplaRegisterDeviceResult.class;
    }
}