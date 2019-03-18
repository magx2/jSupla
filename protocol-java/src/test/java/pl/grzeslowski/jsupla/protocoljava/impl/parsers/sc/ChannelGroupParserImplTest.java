package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ChannelGroupParserImplTest extends AbstractParserTest<ChannelGroup, SuplaChannelGroup> {
    @InjectMocks
    ChannelGroupParserImpl parser;
    @Mock
    StringParser stringParser;

    @Before
    public void setUp() {
        givenStringParser(stringParser);
    }

    @Override
    protected void then(ChannelGroup entity, SuplaChannelGroup supla) {
        assertThat(entity.getEol()).isEqualTo(supla.eol);
        assertThat(entity.getId()).isEqualTo(supla.id);
        assertThat(entity.getLocationId()).isEqualTo(supla.locationId);
        assertThat(entity.getFunction()).isEqualTo(supla.func);
        assertThat(entity.getAltIcon()).isEqualTo(supla.altIcon);
        assertThat(entity.getFlags()).isEqualTo(supla.flags);
        verify(stringParser).parse(supla.caption);
    }

    @Override
    protected Parser<ChannelGroup, SuplaChannelGroup> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelGroup> classToTest() {
        return SuplaChannelGroup.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new ChannelGroupParserImpl(null);
    }

}