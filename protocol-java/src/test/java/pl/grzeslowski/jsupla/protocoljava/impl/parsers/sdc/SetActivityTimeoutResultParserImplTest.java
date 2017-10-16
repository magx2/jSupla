package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class SetActivityTimeoutResultParserImplTest
        extends ParserTest<SetActivityTimeoutResult, SuplaSetActivityTimeoutResult> {
    @InjectMocks SetActivityTimeoutResultParserImpl parser;

    @Override
    protected void then(final SetActivityTimeoutResult entity, final SuplaSetActivityTimeoutResult supla) {
        assertThat(entity.getActivityTimeout()).isEqualTo(supla.activityTimeout);
        assertThat(entity.getMin()).isEqualTo(supla.min);
        assertThat(entity.getMax()).isEqualTo(supla.max);
    }

    @Override
    protected Parser<SetActivityTimeoutResult, SuplaSetActivityTimeoutResult> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaSetActivityTimeoutResult> classToTest() {
        return SuplaSetActivityTimeoutResult.class;
    }
}