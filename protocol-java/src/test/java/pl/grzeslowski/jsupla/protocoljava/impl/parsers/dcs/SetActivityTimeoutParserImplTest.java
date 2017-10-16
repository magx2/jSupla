package pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class SetActivityTimeoutParserImplTest extends ParserTest<SetActivityTimeout, SuplaSetActivityTimeout> {
    @InjectMocks SetActivityTimeoutParserImpl parser;

    @Override
    protected void then(final SetActivityTimeout entity, final SuplaSetActivityTimeout supla) {
        assertThat(entity.getActivityTimeout()).isEqualTo(supla.activityTimeout);
    }

    @Override
    protected Parser<SetActivityTimeout, SuplaSetActivityTimeout> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaSetActivityTimeout> classToTest() {
        return SuplaSetActivityTimeout.class;
    }
}