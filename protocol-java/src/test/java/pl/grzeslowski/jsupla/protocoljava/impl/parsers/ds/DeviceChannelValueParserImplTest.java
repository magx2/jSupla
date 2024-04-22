package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelValueParserImplTest extends AbstractParserTest<DeviceChannelValue, SuplaDeviceChannelValue> {
    @InjectMocks
    DeviceChannelValueParserImpl parser;
    @Mock
    ChannelTypeDecoder channelTypeDecoder;
    @Mock
    DeviceChannelValueParser.TypeMapper typeMapper;

    final ChannelValue channelValue = RANDOM_ENTITY.nextObject(ChannelValue.class);
    final ChannelType channelType = RANDOM_ENTITY.nextObject(ChannelType.class);

    @Override
    protected SuplaDeviceChannelValue given() {
        final SuplaDeviceChannelValue supla = super.given();

        BDDMockito.given(channelTypeDecoder.decode(channelType, supla.value)).willReturn(channelValue);
        BDDMockito.given(typeMapper.findChannelType(supla.channelNumber)).willReturn(channelType);

        return supla;
    }

    @Override
    protected void then(final DeviceChannelValue entity, final SuplaDeviceChannelValue supla) {
        assertThat(entity.getChannelNumber()).isEqualTo(supla.channelNumber);
        assertThat(entity.getValue()).isEqualTo(channelValue);
    }

    @Override
    protected Parser<DeviceChannelValue, SuplaDeviceChannelValue> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaDeviceChannelValue> classToTest() {
        return SuplaDeviceChannelValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new DeviceChannelValueParserImpl(null, typeMapper);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenSuplaDeviceChannelValueToChannelTypeIsNull() {
        new DeviceChannelValueParserImpl(channelTypeDecoder, null);
    }
}