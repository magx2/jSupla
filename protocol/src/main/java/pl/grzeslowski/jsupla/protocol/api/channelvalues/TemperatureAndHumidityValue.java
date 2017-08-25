package pl.grzeslowski.jsupla.protocol.api.channelvalues;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("WeakerAccess")
public final class TemperatureAndHumidityValue implements ChannelValue {
    public final DecimalValue temperature;
    public final DecimalValue humidity;

    public TemperatureAndHumidityValue(final DecimalValue temperature, final DecimalValue humidity) {
        this.temperature = requireNonNull(temperature);
        this.humidity = requireNonNull(humidity);
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
    public int hashCode() {
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
