package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;


import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

public class ChannelBParserImplTest extends AbstractParserTest<ChannelB, SuplaChannelB> {
    @InjectMocks
    ChannelBParserImpl parser;
    @Mock
    ChannelValueParser channelValueParser;
    @Mock
    StringParser stringParser;

    @Override
    protected SuplaChannelB given() {
        final SuplaChannelB supla = super.given();
        BDDMockito.given(channelValueParser.parse(supla.value))
            .willReturn(RANDOM_ENTITY.nextObject(ChannelValue.class));
        BDDMockito.given(stringParser.parse(supla.caption))
            .willReturn(RANDOM_ENTITY.nextObject(String.class));
        return supla;
    }

    @Override
    protected void then(ChannelB entity, SuplaChannelB supla) {
        assertThat(entity.getEol()).isEqualTo(supla.eol);
        assertThat(entity.getId()).isEqualTo(supla.id);
        assertThat(entity.getLocationId()).isEqualTo(supla.locationId);
        assertThat(entity.getFunction()).isEqualTo(supla.func);
        assertThat(entity.isOnline()).isEqualTo(supla.online != 0);
        verify(channelValueParser).parse(supla.value);
        verify(stringParser).parse(supla.caption);

        assertThat(entity.getAltIcon()).isEqualTo(supla.altIcon);
        assertThat(entity.getFlags()).isEqualTo(supla.flags);
        assertThat(entity.getProtocolVersion()).isEqualTo(supla.protocolVersion);
    }

    @Override
    protected Parser<ChannelB, SuplaChannelB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelB> classToTest() {
        return SuplaChannelB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueParserIsNull() {
        new ChannelBParserImpl(null, stringParser);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserParserIsNull() {
        new ChannelBParserImpl(channelValueParser, null);
    }
}