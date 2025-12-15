package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;

class ThermometerTypeChannelEncoderImplTest {
    private final ThermometerTypeChannelEncoderImpl encoder =
            new ThermometerTypeChannelEncoderImpl();

    @Test
    void shouldReturnPlaceholderBytesForTemperature() {
        byte[] bytes = encoder.encode(new TemperatureValue(BigDecimal.TEN));
        assertThat(bytes).hasSize(ProtoConsts.SUPLA_CHANNELVALUE_SIZE);
    }

    @Test
    void shouldReturnPlaceholderBytesForTemperatureAndHumidity() {
        byte[] bytes =
                encoder.encode(new TemperatureAndHumidityValue(BigDecimal.ONE, BigDecimal.TEN));
        assertThat(bytes).hasSize(ProtoConsts.SUPLA_CHANNELVALUE_SIZE);
    }
}
