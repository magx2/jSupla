package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOff;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TimerValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.UnknownValue;

class ChannelTypeDecoderTest {
    private final ChannelTypeDecoder decoder = ChannelTypeDecoder.INSTANCE;

    @Test
    void shouldDecodeRelayType() {
        // given
        byte[] payload = new byte[] {1};

        // when
        ChannelValue value = decoder.decode(ChannelType.SUPLA_CHANNELTYPE_RELAY, payload);

        // then
        assertThat(value).isEqualTo(OnOff.ON);
    }

    @Test
    void shouldDecodeThermometerDoubleType() {
        // given
        byte[] payload =
                ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(-12.5).array();

        // when
        ChannelValue value = decoder.decode(ChannelType.SUPLA_CHANNELTYPE_THERMOMETER, payload);

        // then
        assertThat(value).isInstanceOf(TemperatureValue.class);
        TemperatureValue temperatureValue = (TemperatureValue) value;
        assertThat(temperatureValue.temperature()).isEqualTo(BigDecimal.valueOf(-12.5));
    }

    @Test
    void shouldDecodeUnknownTypeAsUnknownValue() {
        // when
        ChannelValue value = decoder.decode(-1, new byte[0]);

        // then
        assertThat(value).isInstanceOf(UnknownValue.class);
        assertThat(((UnknownValue) value).bytes()).isEmpty();
    }

    @Test
    void shouldFindTimerValueClassForTimerTypes() {
        assertThat(decoder.findClass(ChannelType.EV_TYPE_TIMER_STATE_V1))
                .isEqualTo(TimerValue.class);
        assertThat(decoder.findClass(ChannelType.EV_TYPE_TIMER_STATE_V1_SEC))
                .isEqualTo(TimerValue.class);
    }
}
