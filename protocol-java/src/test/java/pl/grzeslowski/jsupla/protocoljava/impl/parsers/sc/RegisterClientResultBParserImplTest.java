package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;


import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SuppressWarnings("WeakerAccess")
public class RegisterClientResultBParserImplTest
    extends AbstractParserTest<RegisterClientResultB, SuplaRegisterClientResultB> {
    @InjectMocks
    RegisterClientResultBParserImpl parser;

    @Override
    protected void then(RegisterClientResultB entity, SuplaRegisterClientResultB supla) {
        assertThat(entity.getResultCode()).isEqualTo(supla.resultCode);
        assertThat(entity.getClientId()).isEqualTo(supla.clientId);
        assertThat(entity.getLocationCount()).isEqualTo(supla.locationCount);
        assertThat(entity.getChannelCount()).isEqualTo(supla.channelCount);
        assertThat(entity.getActivityTimeout()).isEqualTo(supla.activityTimeout);
        assertThat(entity.getVersion()).isEqualTo(supla.version);
        assertThat(entity.getVersionMin()).isEqualTo(supla.versionMin);
        assertThat(entity.getFlags()).isEqualTo(supla.flags);
    }

    @Override
    protected Parser<RegisterClientResultB, SuplaRegisterClientResultB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaRegisterClientResultB> classToTest() {
        return SuplaRegisterClientResultB.class;
    }
}