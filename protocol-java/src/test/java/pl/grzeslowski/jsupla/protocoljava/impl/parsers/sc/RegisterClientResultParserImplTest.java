package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class RegisterClientResultParserImplTest
        extends AbstractParserTest<RegisterClientResult, SuplaRegisterClientResult> {
    @InjectMocks RegisterClientResultParserImpl parser;

    @Override
    protected void then(final RegisterClientResult entity, final SuplaRegisterClientResult supla) {
        assertThat(entity.getResultCode()).isEqualTo(supla.resultCode);
        assertThat(entity.getClientId()).isEqualTo(supla.clientId);
        assertThat(entity.getLocationCount()).isEqualTo(supla.locationCount);
        assertThat(entity.getChannelCount()).isEqualTo(supla.channelCount);
        assertThat(entity.getActivityTimeout()).isEqualTo(supla.activityTimeout);
        assertThat(entity.getVersion()).isEqualTo(supla.version);
        assertThat(entity.getVersionMin()).isEqualTo(supla.versionMin);
    }

    @Override
    protected Parser<RegisterClientResult, SuplaRegisterClientResult> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterClientResult> classToTest() {
        return SuplaRegisterClientResult.class;
    }
}