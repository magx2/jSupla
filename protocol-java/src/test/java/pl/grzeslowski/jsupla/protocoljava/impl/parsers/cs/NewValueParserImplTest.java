package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.NewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class NewValueParserImplTest extends AbstractParserTest<NewValue, SuplaNewValue> {
    @InjectMocks
    NewValueParserImpl parser;

    @Mock
    ChannelTypeDecoder channelTypeDecoder;
    @Mock
    DeviceChannelValueParser.TypeMapper typeMapper;
    @Mock
    ChannelValue channelValue;
    final ChannelType channelType = ChannelType.UNKNOWN;

    @Override
    protected SuplaNewValue given() {
        SuplaNewValue suplaNewValue = super.given();
        BDDMockito.given(typeMapper.findChannelType(suplaNewValue.id)).willReturn(channelType);
        BDDMockito.given(channelTypeDecoder.decode(channelType, suplaNewValue.value)).willReturn(channelValue);
        return suplaNewValue;
    }

    @Override
    protected void then(NewValue entity, SuplaNewValue supla) {
        assertThat(entity.getId()).isEqualTo(supla.id);
        assertThat(entity.getTarget()).isEqualTo(supla.target);
        assertThat(entity.getValue()).isEqualTo(channelValue);
        verify(channelTypeDecoder).decode(channelType, supla.value);
    }

    @Override
    protected Parser<NewValue, SuplaNewValue> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaNewValue> classToTest() {
        return SuplaNewValue.class;
    }
}