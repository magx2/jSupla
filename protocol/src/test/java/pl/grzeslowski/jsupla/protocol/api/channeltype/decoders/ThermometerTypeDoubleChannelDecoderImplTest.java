package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.val;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ThermometerTypeDoubleChannelDecoderImplTest {
    ThermometerTypeDoubleChannelDecoderImpl decoder = new ThermometerTypeDoubleChannelDecoderImpl();

    @Test
    public void auratonTestCase() {
        // given
        byte[] data = new byte[]{0, 0, 0, 0, 0, 48, 113, -64};

        // when
        ChannelValue decode = decoder.decode(data);

        // then
        assertThat(decode).isInstanceOf(TemperatureValue.class);
        val temperature = (TemperatureValue) decode;
        assertThat(temperature.temperature()).isEqualTo(BigDecimal.valueOf(-275));
    }

    @Test
    public void auratonTestCase2() {
        // given
        byte[] data = new byte[]{102, 102, 102, 102, 102, 102, 57, 64};

        // when
        ChannelValue decode = decoder.decode(data);

        // then
        assertThat(decode).isInstanceOf(TemperatureValue.class);
        val temperature = (TemperatureValue) decode;
        assertThat(temperature.temperature()).isBetween(BigDecimal.valueOf(25.3), BigDecimal.valueOf(25.4));
    }
}