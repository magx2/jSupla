package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
@Deprecated
public class ChannelNewValueParserImplTest extends ParserTest<ChannelNewValue, SuplaChannelNewValue> {
    @InjectMocks ChannelNewValueParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;

    @Override
    protected SuplaChannelNewValue given() {
        final SuplaChannelNewValue supla = super.given();
        BDDMockito.given(channelTypeDecoder.decode(supla)).willReturn(new ChannelValue() {
        });
        return supla;
    }

    @Override
    protected void then(final ChannelNewValue entity, final SuplaChannelNewValue supla) {
        assertThat(entity.getChannelId()).isEqualTo(supla.channelId);
        verify(channelTypeDecoder).decode(supla);
    }

    @Override
    protected Parser<ChannelNewValue, SuplaChannelNewValue> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelNewValue> classToTest() {
        return SuplaChannelNewValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new ChannelNewValueParserImpl(null);
    }
}