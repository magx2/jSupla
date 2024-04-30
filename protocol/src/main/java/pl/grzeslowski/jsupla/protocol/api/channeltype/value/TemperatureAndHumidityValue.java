package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
public class TemperatureAndHumidityValue implements ChannelValue {
    BigDecimal temperature;
    BigDecimal humidity;

    public TemperatureAndHumidityValue(final int temperature, final int humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }

    public TemperatureAndHumidityValue(final long temperature, final long humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }

    public TemperatureAndHumidityValue(final double temperature, final double humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }

    public TemperatureAndHumidityValue(final float temperature, final float humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }

    public TemperatureAndHumidityValue(final byte temperature, final byte humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }

    public TemperatureAndHumidityValue(final short temperature, final short humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }

    public TemperatureAndHumidityValue(final String temperature, final String humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }
}
