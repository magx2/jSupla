package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueBParserImplTest extends ParserTest<ChannelNewValueB, SuplaChannelNewValueB> {
    @InjectMocks ChannelNewValueBParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;

    @Override
    protected SuplaChannelNewValueB given() {
        final SuplaChannelNewValueB supla = super.given();
        when(channelTypeDecoder.decode(supla)).thenReturn(new ChannelValue() {
        });
        return supla;
    }

    @Override
    protected void then(final ChannelNewValueB entity, final SuplaChannelNewValueB supla) {
        assertThat(entity.getChannelId()).isEqualTo(supla.channelId);
        verify(channelTypeDecoder).decode(supla);
    }

    @Override
    protected Parser<ChannelNewValueB, SuplaChannelNewValueB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelNewValueB> classToTest() {
        return SuplaChannelNewValueB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullChannelTypeDecoder() {
        new ChannelNewValueBParserImpl(null);
    }
}