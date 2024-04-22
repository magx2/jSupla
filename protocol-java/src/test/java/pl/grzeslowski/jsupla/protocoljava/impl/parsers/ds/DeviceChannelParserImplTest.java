package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelParserImplTest extends AbstractParserTest<DeviceChannel, SuplaDeviceChannel> {
    @InjectMocks
    DeviceChannelParserImpl parser;
    @Mock
    ChannelTypeDecoder channelTypeDecoder;
    @Mock
    SuplaDeviceChannelToChannelType suplaDeviceChannelToChannelType;

    final ChannelValue value = RANDOM_ENTITY.nextObject(ChannelValue.class);
    final ChannelType channelType = RANDOM_ENTITY.nextObject(ChannelType.class);

    @Override
    protected SuplaDeviceChannel given() {
        final SuplaDeviceChannel supla = super.given();

        BDDMockito.given(suplaDeviceChannelToChannelType.toChannelType(supla)).willReturn(channelType);
        BDDMockito.given(channelTypeDecoder.decode(channelType, supla.value)).willReturn(value);

        return supla;
    }

    @Override
    protected void then(final DeviceChannel entity, final SuplaDeviceChannel supla) {
        assertThat(entity.getNumber()).isEqualTo(supla.number);
        assertThat(entity.getType()).isEqualTo(supla.type);
        assertThat(entity.getValue()).isEqualTo(value);
    }

    @Override
    protected Parser<DeviceChannel, SuplaDeviceChannel> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaDeviceChannel> classToTest() {
        return SuplaDeviceChannel.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new DeviceChannelParserImpl(null, suplaDeviceChannelToChannelType);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenSuplaDeviceChannelToChannelTypeIsNull() {
        new DeviceChannelParserImpl(channelTypeDecoder, null);
    }
}