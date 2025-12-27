package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;

/**
 * @param temperature
 * @param humidity
 */
public record TemperatureAndHumidityValue(BigDecimal temperature, BigDecimal humidity)
        implements ChannelValue {
    public TemperatureAndHumidityValue(final int temperature, final int humidity) {
        this(BigDecimal.valueOf(temperature), BigDecimal.valueOf(humidity));
    }

    public TemperatureAndHumidityValue(final long temperature, final long humidity) {
        this(BigDecimal.valueOf(temperature), BigDecimal.valueOf(humidity));
    }

    public TemperatureAndHumidityValue(final double temperature, final double humidity) {
        this(BigDecimal.valueOf(temperature), BigDecimal.valueOf(humidity));
    }

    public TemperatureAndHumidityValue(final float temperature, final float humidity) {
        this(BigDecimal.valueOf(temperature), BigDecimal.valueOf(humidity));
    }

    public TemperatureAndHumidityValue(final byte temperature, final byte humidity) {
        this(BigDecimal.valueOf(temperature), BigDecimal.valueOf(humidity));
    }

    public TemperatureAndHumidityValue(final short temperature, final short humidity) {
        this(BigDecimal.valueOf(temperature), BigDecimal.valueOf(humidity));
    }

    public TemperatureAndHumidityValue(final String temperature, final String humidity) {
        this(new BigDecimal(temperature), new BigDecimal(humidity));
    }
}
