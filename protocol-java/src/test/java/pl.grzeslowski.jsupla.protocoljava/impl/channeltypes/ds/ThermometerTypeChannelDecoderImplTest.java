package pl.grzeslowski.jsupla.protocoljava.impl.channeltypes.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureValue;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.ThermometerTypeChannelDecoderImpl;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("WeakerAccess")
public class ThermometerTypeChannelDecoderImplTest {
    static final int MINIMAL_SIZE = 4;
    @InjectMocks ThermometerTypeChannelDecoderImpl decoder;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfSizeIsTooSmall() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[offset + MINIMAL_SIZE - 1];

        // when
        decoder.decode(bytes, offset);
    }

    @Test
    public void shouldShouldParseOnlyTemperature() throws Exception {

        // given
        final double temp = 22.112;
        final int offset = 100;
        final byte[] bytes = new byte[offset + INT_SIZE];
        INSTANCE.writeInteger((int) (temp * 1000), bytes, offset);

        // when
        final ChannelValue value = decoder.decode(bytes, offset);

        // then
        assertThat(value).isOfAnyClassIn(TemperatureValue.class);
        TemperatureValue temperatureValue = (TemperatureValue) value;
        assertThat(temperatureValue.temperature).isEqualTo(new BigDecimal("22.112"));
    }

    @SuppressWarnings("UnusedAssignment")
    @Test
    public void shouldShouldParseTemperatureAndHumidity() throws Exception {

        // given
        final double temp = 22.112;
        final double humidity = 30.053;
        final int initialOffset = 100;
        int offset = initialOffset;
        final byte[] bytes = new byte[initialOffset + INT_SIZE * 2];
        offset += INSTANCE.writeInteger((int) (temp * 1000), bytes, offset);
        offset += INSTANCE.writeInteger((int) (humidity * 1000), bytes, offset);

        // when
        final ChannelValue value = decoder.decode(bytes, initialOffset);

        // then
        assertThat(value).isOfAnyClassIn(TemperatureAndHumidityValue.class);
        TemperatureAndHumidityValue temperatureValue = (TemperatureAndHumidityValue) value;
        assertThat(temperatureValue.temperature).isEqualTo(new BigDecimal("22.112"));
        assertThat(temperatureValue.humidity).isEqualTo(new BigDecimal("30.053"));
    }
}