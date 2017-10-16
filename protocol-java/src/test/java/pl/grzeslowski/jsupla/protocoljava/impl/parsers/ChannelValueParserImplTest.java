package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class ChannelValueParserImplTest extends ParserTest<ChannelValue, SuplaChannelValue> {
    @InjectMocks ChannelValueParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;

    @Override
    protected SuplaChannelValue given() {
        BDDMockito.given(channelTypeDecoder.decode(any(byte[].class)))
                .willReturn(new pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue() {
                });
        BDDMockito.given(channelTypeDecoder.decodeNullable(any(byte[].class)))
                .willReturn(new pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue() {
                });
        return super.given();
    }

    @Override
    protected void then(final ChannelValue entity, final SuplaChannelValue supla) {
        verify(channelTypeDecoder).decode(supla.value);
        verify(channelTypeDecoder).decodeNullable(supla.subValue);
    }

    @Override
    protected Parser<ChannelValue, SuplaChannelValue> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelValue> classToTest() {
        return SuplaChannelValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new ChannelValueParserImpl(null);
    }
}