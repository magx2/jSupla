package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelBToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.AbstractParserTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelBParserImplTest extends AbstractParserTest<DeviceChannelB, SuplaDeviceChannelB> {
    @InjectMocks DeviceChannelBParserImpl parser;
    @Mock ChannelTypeDecoder channelTypeDecoder;
    @Mock SuplaDeviceChannelBToChannelType suplaDeviceChannelBToChannelType;

    final ChannelValue channelValue = RANDOM_ENTITY.nextObject(ChannelValue.class);
    final ChannelType channelType = RANDOM_ENTITY.nextObject(ChannelType.class);

    @Override
    protected SuplaDeviceChannelB given() {
        final SuplaDeviceChannelB supla = super.given();

        BDDMockito.given(channelTypeDecoder.decode(channelType, supla.value)).willReturn(channelValue);
        BDDMockito.given(suplaDeviceChannelBToChannelType.toChannelType(supla)).willReturn(channelType);

        return supla;
    }

    @Override
    protected void then(final DeviceChannelB entity, final SuplaDeviceChannelB supla) {
        assertThat(entity.getNumber()).isEqualTo(supla.number);
        assertThat(entity.getType()).isEqualTo(supla.type);
        assertThat(entity.getFunction()).isEqualTo(supla.funcList);
        assertThat(entity.getDefaultValue()).isEqualTo(supla.defaultValue);
        assertThat(entity.getValue()).isEqualTo(channelValue);
    }

    @Override
    protected Parser<DeviceChannelB, SuplaDeviceChannelB> parser() {
        return parser;
    }

    @Override
    protected Class<SuplaDeviceChannelB> classToTest() {
        return SuplaDeviceChannelB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeDecoderIsNull() {
        new DeviceChannelBParserImpl(null, suplaDeviceChannelBToChannelType);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenSuplaDeviceChannelBToChannelTypeIsNull() {
        new DeviceChannelBParserImpl(channelTypeDecoder, null);
    }
}