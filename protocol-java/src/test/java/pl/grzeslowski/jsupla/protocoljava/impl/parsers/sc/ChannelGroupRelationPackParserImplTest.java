package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelGroupRelationParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

public class ChannelGroupRelationPackParserImplTest
    extends AbstractParserTest<ChannelGroupRelationPack, SuplaChannelGroupRelationPack> {
    @InjectMocks
    ChannelGroupRelationPackParserImpl parser;
    @Mock
    ChannelGroupRelationParser channelGroupRelationParser;

    @Override
    protected SuplaChannelGroupRelationPack given() {
        BDDMockito.given(channelGroupRelationParser.parse(any()))
                .willReturn(RANDOM_SUPLA.nextObject(ChannelGroupRelation.class));
        return super.given();
    }

    @Override
    protected void then(ChannelGroupRelationPack entity, SuplaChannelGroupRelationPack supla) {
        assertThat(entity.getTotalLeft()).isEqualTo(supla.totalLeft);
        assertThat(entity.getItems().size()).isEqualTo(supla.count);
        for (SuplaChannelGroupRelation item : supla.items) {
            verify(channelGroupRelationParser).parse(item);
        }
    }

    @Override
    protected Parser<ChannelGroupRelationPack, SuplaChannelGroupRelationPack> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelGroupRelationPack> classToTest() {
        return SuplaChannelGroupRelationPack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullChannelGroupRelationParser() {
        new ChannelGroupRelationPackParserImpl(null);
    }
}