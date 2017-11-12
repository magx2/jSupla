package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class ChannelValueParserImplTest extends AbstractParserTest<ChannelValue, SuplaChannelValue> {
    @InjectMocks ChannelValueParserImpl parser;
    @Mock ChannelValueParser channelValueParser;

    @Override
    protected SuplaChannelValue given() {
        final SuplaChannelValue supla = super.given();
        BDDMockito.given(channelValueParser.parse(supla.value)).willReturn(
                RANDOM_ENTITY.nextObject(pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue.class)
        );
        return supla;
    }

    @Override
    protected void then(final ChannelValue entity, final SuplaChannelValue supla) {
        assertThat(entity.getEol()).isEqualTo(supla.eol);
        assertThat(entity.getId()).isEqualTo(supla.id);
        assertThat(entity.isOnline()).isEqualTo(supla.online != 0);
        verify(channelValueParser).parse(supla.value);
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
    public void shouldThrowNullPointerExceptionWhenChannelValueParserIsNull() {
        new ChannelValueParserImpl(null);
    }
}