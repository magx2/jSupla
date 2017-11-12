package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class ChannelValueParserImplTest extends AbstractParserTest<ChannelValue, SuplaChannelValue> {
    @InjectMocks ChannelValueParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;
    @Mock SuplaChannelValueToChannelType suplaChannelValueToChannelType;

    final pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue value =
            RANDOM_ENTITY.nextObject(pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue.class);
    final pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue subValue =
            RANDOM_ENTITY.nextObject(pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue.class);

    final ChannelType channelType = RANDOM_ENTITY.nextObject(ChannelType.class);
    final ChannelType channelTypeSubValue = RANDOM_ENTITY.nextObject(ChannelType.class);

    @Override
    protected SuplaChannelValue given() {
        final SuplaChannelValue supla = super.given();

        BDDMockito.given(channelTypeDecoder.decode(channelType, supla.value)).willReturn(value);
        BDDMockito.given(channelTypeDecoder.decode(channelTypeSubValue, supla.subValue))
                .willReturn(subValue);

        BDDMockito.given(suplaChannelValueToChannelType.toChannelType(supla)).willReturn(channelType);
        BDDMockito.given(suplaChannelValueToChannelType.toChannelTypeSubValue(supla)).willReturn(channelTypeSubValue);

        return supla;
    }

    @Override
    protected void then(final ChannelValue entity, final SuplaChannelValue supla) {
        assertThat(entity.getValue()).isEqualTo(value);
        assertThat(entity.getSubValue()).isEqualTo(Optional.of(subValue));
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
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new ChannelValueParserImpl(null, suplaChannelValueToChannelType);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenSuplaChannelValueToChannelTypeIsNull() {
        new ChannelValueParserImpl(channelTypeDecoder, null);
    }
}