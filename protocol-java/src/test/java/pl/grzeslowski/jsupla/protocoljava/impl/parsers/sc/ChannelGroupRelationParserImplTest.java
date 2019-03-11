package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;


public class ChannelGroupRelationParserImplTest extends AbstractParserTest<ChannelGroupRelation, SuplaChannelGroupRelation> {
    @InjectMocks
    ChannelGroupRelationParserImpl parser;

    @Override
    protected void then(ChannelGroupRelation entity, SuplaChannelGroupRelation supla) {
        assertThat(entity.getEol()).isEqualTo((int) supla.eol);
        assertThat(entity.getChannelId()).isEqualTo(supla.channelId);
        assertThat(entity.getChannelGroupId()).isEqualTo(supla.channelGroupId);
    }

    @Override
    protected Parser<ChannelGroupRelation, SuplaChannelGroupRelation> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelGroupRelation> classToTest() {
        return SuplaChannelGroupRelation.class;
    }
}