package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;

/**
 * @param temperature
 */
public record TemperatureValue(BigDecimal temperature) implements ChannelValue {
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
}
