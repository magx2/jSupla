package pl.grzeslowski.jsupla.protocoljava.api.channels.values;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("WeakerAccess")
public final class TemperatureAndHumidityValue implements ChannelValue {
    public final BigDecimal temperature;
    public final BigDecimal humidity;

    public TemperatureAndHumidityValue(final BigDecimal temperature, final BigDecimal humidity) {
        this.temperature = requireNonNull(temperature);
        this.humidity = requireNonNull(humidity);
    }

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemperatureAndHumidityValue)) {
            return false;
        }

        final TemperatureAndHumidityValue that = (TemperatureAndHumidityValue) o;

        if (!temperature.equals(that.temperature)) {
            return false;
        }
        return humidity.equals(that.humidity);
    }

    @Override
    public final int hashCode() {
        int result = temperature.hashCode();
        result = 31 * result + humidity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TemperatureAndHumidityValue{" +
            "temperature=" + temperature +
            ", humidity=" + humidity +
            '}';
    }
}
