package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.DOUBLE_SIZE;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

class ThermometerTypeDoubleChannelEncoderImplTest {
    private final ThermometerTypeDoubleChannelEncoderImpl encoder =
            new ThermometerTypeDoubleChannelEncoderImpl();

    @Test
    void shouldEncodeTemperature() {
        // given
        final double expectedTemperature = 23.45;
        final TemperatureValue value =
                new TemperatureValue(BigDecimal.valueOf(expectedTemperature));

        // when
        byte[] bytes = encoder.encode(value);

        // then
        assertThat(bytes).hasSize(DOUBLE_SIZE);
        final double actualTemperature = PrimitiveDecoder.INSTANCE.parseDouble(bytes, 0);
        assertThat(actualTemperature).isEqualTo(expectedTemperature);
    }
}
