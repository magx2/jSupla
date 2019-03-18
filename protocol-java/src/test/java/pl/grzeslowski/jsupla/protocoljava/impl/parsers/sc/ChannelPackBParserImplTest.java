package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


public class ChannelPackBParserImplTest extends AbstractParserTest<ChannelPackB, SuplaChannelPackB> {
    @InjectMocks
    ChannelPackBParserImpl parser;
    @Mock
    ChannelBParser channelBParser;

    @SuppressWarnings("unchecked")
    @Override
    protected void then(ChannelPackB entity, SuplaChannelPackB supla) {
        assertThat(entity.getTotalLeft()).isEqualTo(supla.totalLeft);
        assertThat(entity.getChannels()).hasSize(supla.count);
        for (SuplaChannelB channel : supla.channels) {
            verify(channelBParser).parse(channel);
        }
    }

    @Override
    protected Parser<ChannelPackB, SuplaChannelPackB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelPackB> classToTest() {
        return SuplaChannelPackB.class;
    }
}