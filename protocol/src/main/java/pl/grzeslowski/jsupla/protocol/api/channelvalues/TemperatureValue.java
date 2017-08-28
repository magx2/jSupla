package pl.grzeslowski.jsupla.protocol.api.channelvalues;

import java.math.BigDecimal;

@SuppressWarnings("WeakerAccess")
public final class TemperatureValue implements ChannelValue {
    public final BigDecimal temperature;

    @SuppressWarnings("WeakerAccess")
    public TemperatureValue(final BigDecimal temperature) {
        this.temperature = temperature;
    }

    public TemperatureValue(final int value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final long value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final double value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final float value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final byte value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final short value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final String value) {
        this(new BigDecimal(value));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemperatureValue)) {
            return false;
        }

        final TemperatureValue that = (TemperatureValue) o;

        return temperature.equals(that.temperature);
    }

    @Override
    public int hashCode() {
        return temperature.hashCode();
    }

    @Override
    public String toString() {
        return "TemperatureValue{" +
                       "temperature=" + temperature +
                       '}';
    }
}
