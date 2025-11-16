package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;

public class ThermometerTypeChannelDecoderImplTest {
    private final ThermometerTypeChannelDecoderImpl decoder =
            new ThermometerTypeChannelDecoderImpl();

    @Test
    public void shouldDecodeTemperatureOnly() {
        // given
        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(25_000);

        // when
        ChannelValue value = decoder.decode(buffer.array());

        // then
        assertThat(value).isInstanceOf(TemperatureValue.class);
        TemperatureValue temperature = (TemperatureValue) value;
        assertThat(temperature.getTemperature()).isEqualByComparingTo(new BigDecimal("25.000"));
    }

    @Test
    public void shouldDecodeTemperatureAndHumidity() {
        // given
        ByteBuffer buffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(-5_500);
        buffer.putInt(55_000);

        // when
        ChannelValue value = decoder.decode(buffer.array());

        // then
        assertThat(value).isInstanceOf(TemperatureAndHumidityValue.class);
        TemperatureAndHumidityValue temperature = (TemperatureAndHumidityValue) value;
        assertThat(temperature.getTemperature()).isEqualByComparingTo(new BigDecimal("-5.500"));
        assertThat(temperature.getHumidity()).isEqualByComparingTo(new BigDecimal("55.000"));
    }
}
