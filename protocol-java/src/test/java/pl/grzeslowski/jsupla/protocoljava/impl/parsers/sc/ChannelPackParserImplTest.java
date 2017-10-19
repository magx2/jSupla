package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class ChannelPackParserImplTest extends ParserTest<ChannelPack, SuplaChannelPack> {
    @InjectMocks ChannelPackParserImpl parser;
    @Mock ChannelParser channelParser;

    @Override
    protected SuplaChannelPack given() {
        final SuplaChannelPack supla = super.given();
        BDDMockito.given(channelParser.parse(any())).willReturn(RANDOM_ENTITY.nextObject(Channel.class));
        return supla;
    }

    @Override
    protected void then(final ChannelPack entity, final SuplaChannelPack supla) {
        assertThat(entity.getTotalLeft()).isEqualTo(supla.totalLeft);
        Arrays.stream(supla.channels)
                .forEach(channel -> verify(channelParser).parse(channel));
        verifyNoMoreInteractions(channelParser);
    }

    @Override
    protected Parser<ChannelPack, SuplaChannelPack> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelPack> classToTest() {
        return SuplaChannelPack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelParserIsNull() {
        new ChannelPackParserImpl(null);
    }
}