package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class TimevalParserImplTest extends ParserTest<Timeval, SuplaTimeval> {
    @InjectMocks TimevalParserImpl parser;

    @Override
    protected void then(final Timeval entity, final SuplaTimeval supla) {
        assertThat(entity.getSeconds()).isEqualTo(supla.seconds);
        assertThat(entity.getMilliseconds()).isEqualTo(supla.milliseconds);
    }

    @Override
    protected Parser<Timeval, SuplaTimeval> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaTimeval> classToTest() {
        return SuplaTimeval.class;
    }
}