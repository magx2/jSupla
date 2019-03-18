package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

public class ChannelValuePackParserImplTest extends AbstractParserTest<ChannelValuePack, SuplaChannelValuePack> {
    @InjectMocks
    ChannelValuePackParserImpl parser;
    @Mock
    ChannelValueParser channelValueParser;

    @Override
    protected SuplaChannelValuePack given() {
        BDDMockito.given(channelValueParser.parse(any())).willReturn(RANDOM_ENTITY.nextObject(ChannelValue.class));
        return super.given();
    }

    @Override
    protected void then(ChannelValuePack entity, SuplaChannelValuePack supla) {
        assertThat(entity.getItems()).hasSize(supla.count);
        assertThat(entity.getTotalLeft()).isEqualTo(supla.totalLeft);
        for (SuplaChannelValue item : supla.items) {
            verify(channelValueParser).parse(item);
        }
    }

    @Override
    protected Parser<ChannelValuePack, SuplaChannelValuePack> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelValuePack> classToTest() {
        return SuplaChannelValuePack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueParserIsNull() {
        new ChannelValuePackParserImpl(null);
    }

}