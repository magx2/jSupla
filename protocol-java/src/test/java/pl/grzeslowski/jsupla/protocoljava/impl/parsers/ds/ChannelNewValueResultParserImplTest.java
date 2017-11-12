package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueResultParserImplTest
        extends AbstractParserTest<ChannelNewValueResult, SuplaChannelNewValueResult> {
    @InjectMocks ChannelNewValueResultParserImpl parser;

    @Override
    protected void then(final ChannelNewValueResult entity, final SuplaChannelNewValueResult supla) {
        assertThat(entity.getChannelNumber()).isEqualTo(supla.channelNumber);
        assertThat(entity.getSenderId()).isEqualTo(supla.senderId);
        assertThat(entity.isSuccess()).isEqualTo(supla.success != 0);
    }

    @Override
    protected Parser<ChannelNewValueResult, SuplaChannelNewValueResult> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelNewValueResult> classToTest() {
        return SuplaChannelNewValueResult.class;
    }
}