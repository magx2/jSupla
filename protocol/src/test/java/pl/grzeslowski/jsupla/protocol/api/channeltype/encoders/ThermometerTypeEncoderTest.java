package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

class ThermometerTypeEncoderTest {
    private final ThermometerTypeEncoder encoder = new ThermometerTypeEncoder();

    @Test
    void shouldEncodeTemperatureAndHumidity() {
        // given
        final BigDecimal temp = BigDecimal.valueOf(23.45);
        final BigDecimal humidity = BigDecimal.valueOf(54.32);
        final TemperatureAndHumidityValue value = new TemperatureAndHumidityValue(temp, humidity);

        // when
        byte[] bytes = encoder.encode(value);

        // then
        assertThat(bytes).hasSize(SUPLA_CHANNELVALUE_SIZE);
        final int actualTemp = PrimitiveDecoder.INSTANCE.parseInt(bytes, 0);
        assertThat(actualTemp).isEqualTo(23450);
        final int actualHumidity = PrimitiveDecoder.INSTANCE.parseInt(bytes, INT_SIZE);
        assertThat(actualHumidity).isEqualTo(54320);
    }
}
