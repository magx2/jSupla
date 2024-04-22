package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueBParserImplTest extends AbstractParserTest<ChannelNewValueB, SuplaChannelNewValueB> {
    @InjectMocks
    ChannelNewValueBParserImpl parser;
    @Mock
    ChannelTypeDecoder channelTypeDecoder;
    @Mock
    DeviceChannelValueParser.TypeMapper typeMapper;

    final ChannelValue channelValue = RANDOM_ENTITY.nextObject(ChannelValue.class);
    final ChannelType channelType = RANDOM_ENTITY.nextObject(ChannelType.class);

    @Override
    protected SuplaChannelNewValueB given() {
        final SuplaChannelNewValueB supla = super.given();

        BDDMockito.given(channelTypeDecoder.decode(channelType, supla.value)).willReturn(channelValue);
        BDDMockito.given(typeMapper.findChannelType(supla.channelId)).willReturn(channelType);

        return supla;
    }

    @Override
    protected void then(final ChannelNewValueB entity, final SuplaChannelNewValueB supla) {
        assertThat(entity.getChannelId()).isEqualTo(supla.channelId);
        assertThat(entity.getValue()).isEqualTo(channelValue);
    }

    @Override
    protected Parser<ChannelNewValueB, SuplaChannelNewValueB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelNewValueB> classToTest() {
        return SuplaChannelNewValueB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullChannelTypeDecoder() {
        new ChannelNewValueBParserImpl(null, typeMapper);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullSuplaChannelNewValueBToChannelType() {
        new ChannelNewValueBParserImpl(channelTypeDecoder, null);
    }
}