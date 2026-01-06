package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;

class ThermometerTypeDecoderTest {
    private final ThermometerTypeDecoder decoder = new ThermometerTypeDecoder();

    @Test
    void shouldDecodeTemperatureAndHumidity() {
        // given
        ByteBuffer buffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(-5_500);
        buffer.putInt(55_000);

        // when
        var temperature = decoder.decode(buffer.array());

        // then
        assertThat(temperature.temperature()).isEqualByComparingTo(new BigDecimal("-5.500"));
        assertThat(temperature.humidity().humidity())
                .isEqualByComparingTo(new BigDecimal("55.000"));
    }
}
