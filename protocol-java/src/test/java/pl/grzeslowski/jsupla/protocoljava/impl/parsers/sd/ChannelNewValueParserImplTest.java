package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SdSuplaChannelNewValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueParserImplTest extends ParserTest<ChannelNewValue, SuplaChannelNewValue> {
    @InjectMocks ChannelNewValueParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;
    @Mock SdSuplaChannelNewValueToChannelType suplaChannelNewValueToChannelType;

    final ChannelValue channelValue = RANDOM_ENTITY.nextObject(ChannelValue.class);
    final ChannelType channelType = RANDOM_ENTITY.nextObject(ChannelType.class);

    @Override
    protected SuplaChannelNewValue given() {
        final SuplaChannelNewValue supla = super.given();

        BDDMockito.given(channelTypeDecoder.decode(channelType, supla.value)).willReturn(channelValue);
        BDDMockito.given(suplaChannelNewValueToChannelType.toChannelType(supla)).willReturn(channelType);

        return supla;
    }

    @Override
    protected void then(final ChannelNewValue entity, final SuplaChannelNewValue supla) {
        assertThat(entity.getSenderId()).isEqualTo(supla.senderId);
        assertThat(entity.getChannelNumber()).isEqualTo(supla.channelNumber);
        assertThat(entity.getDurationMs()).isEqualTo(supla.durationMs);
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
        new ChannelNewValueParserImpl(null, suplaChannelNewValueToChannelType);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenSdSuplaChannelNewValueToChannelTypeIsNull() {
        new ChannelNewValueParserImpl(channelTypeDecoder, null);
    }
}