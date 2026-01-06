package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureDoubleValue;

class ThermometerDoubleTypeDecoderTest {
    ThermometerDoubleTypeDecoder decoder = new ThermometerDoubleTypeDecoder();

    @Test
    void auratonTestCase() {
        // given
        byte[] data = new byte[] {0, 0, 0, 0, 0, 48, 113, -64};

        // when
        var temperature = decoder.decode(data);

        // then
        assertThat(temperature).isInstanceOf(TemperatureDoubleValue.class);
        assertThat(temperature.temperature()).isEqualTo(BigDecimal.valueOf(-275.0));
    }

    @Test
    void auratonTestCase2() {
        // given
        byte[] data = new byte[] {102, 102, 102, 102, 102, 102, 57, 64};

        // when
        var temperature = decoder.decode(data);

        // then
        assertThat(temperature).isInstanceOf(TemperatureDoubleValue.class);
        assertThat(temperature.temperature())
                .isBetween(BigDecimal.valueOf(25.3), BigDecimal.valueOf(25.4));
    }
}
