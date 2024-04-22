package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class LocationParserImplTest extends AbstractParserTest<Location, SuplaLocation> {
    @InjectMocks
    LocationParserImpl parser;
    @Mock
    StringParser stringParser;

    @Override
    protected SuplaLocation given() {
        givenStringParser(stringParser);
        return super.given();
    }

    @Override
    protected void then(final Location entity, final SuplaLocation supla) {
        assertThat(entity.getEol()).isEqualTo(supla.eol);
        assertThat(entity.getId()).isEqualTo(supla.id);
        verify(stringParser).parse(supla.caption);
    }

    @Override
    protected Parser<Location, SuplaLocation> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaLocation> classToTest() {
        return SuplaLocation.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new LocationParserImpl(null);
    }

}