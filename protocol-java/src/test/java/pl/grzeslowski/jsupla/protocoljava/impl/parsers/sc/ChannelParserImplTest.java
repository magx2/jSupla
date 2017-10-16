package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class ChannelParserImplTest extends ParserTest<Channel, SuplaChannel> {
    @InjectMocks ChannelParserImpl parser;
    @Mock ChannelValueParser channelValueParser;
    @Mock StringParser stringParser;

    @Override
    protected SuplaChannel given() {
        final SuplaChannel supla = super.given();
        BDDMockito.given(channelValueParser.parse(supla.value))
                .willReturn(RANDOM_ENTITY.nextObject(ChannelValue.class));
        BDDMockito.given(stringParser.parse(supla.caption))
                .willReturn(RANDOM_ENTITY.nextObject(String.class));
        return supla;
    }

    @Override
    protected void then(final Channel entity, final SuplaChannel supla) {
        assertThat(entity.getEol()).isEqualTo(supla.eol);
        assertThat(entity.getId()).isEqualTo(supla.id);
        assertThat(entity.getLocationId()).isEqualTo(supla.locationId);
        assertThat(entity.getFunction()).isEqualTo(supla.func);
        assertThat(entity.isOnline()).isEqualTo(supla.online != 0);
        verify(channelValueParser).parse(supla.value);
        verify(stringParser).parse(supla.caption);
    }

    @Override
    protected Parser<Channel, SuplaChannel> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannel> classToTest() {
        return SuplaChannel.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueParserIsNull() {
        new ChannelParserImpl(null, stringParser);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserParserIsNull() {
        new ChannelParserImpl(channelValueParser, null);
    }
}