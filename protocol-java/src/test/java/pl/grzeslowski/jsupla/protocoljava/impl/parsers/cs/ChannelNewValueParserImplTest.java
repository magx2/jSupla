package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueParserImplTest extends AbstractParserTest<ChannelNewValue, SuplaChannelNewValue> {
    @InjectMocks
    ChannelNewValueParserImpl parser;
    @Mock
    ChannelTypeDecoder channelTypeDecoder;
    @Mock
    DeviceChannelValueParser.TypeMapper typeMapper;

    final ChannelValue channelValue = RANDOM_ENTITY.nextObject(ChannelValue.class);
    final ChannelType channelType = RANDOM_ENTITY.nextObject(ChannelType.class);

    @Override
    protected SuplaChannelNewValue given() {
        final SuplaChannelNewValue supla = super.given();

        BDDMockito.given(channelTypeDecoder.decode(channelType, supla.value)).willReturn(channelValue);
        BDDMockito.given(typeMapper.findChannelType(supla.channelId)).willReturn(channelType);

        return supla;
    }

    @Override
    protected void then(final ChannelNewValue entity, final SuplaChannelNewValue supla) {
        assertThat(entity.getChannelId()).isEqualTo(supla.channelId);
        assertThat(entity.getValue()).isEqualTo(channelValue);
    }

    @Override
    protected Parser<ChannelNewValue, SuplaChannelNewValue> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaChannelNewValue> classToTest() {
        return SuplaChannelNewValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new ChannelNewValueParserImpl(null, typeMapper);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenSuplaChannelNewValueToChannelTypeIsNull() {
        new ChannelNewValueParserImpl(channelTypeDecoder, null);
    }
}