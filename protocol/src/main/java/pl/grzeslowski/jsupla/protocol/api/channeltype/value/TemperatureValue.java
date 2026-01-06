package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import lombok.NonNull;

/**
 * @param temperature
 */
public record TemperatureValue(@NonNull BigDecimal temperature) implements ChannelValue {
    public TemperatureValue(final int value) {
        this(BigDecimal.valueOf(value));
    }

    public TemperatureValue(final long value) {
        this(BigDecimal.valueOf(value));
    }

    public TemperatureValue(final double value) {
        this(BigDecimal.valueOf(value));
    }

    public TemperatureValue(final float value) {
        this(BigDecimal.valueOf(value));
    }

    public TemperatureValue(final byte value) {
        this(BigDecimal.valueOf(value));
    }

    public TemperatureValue(final short value) {
        this(BigDecimal.valueOf(value));
    }

    public TemperatureValue(final String value) {
        this(new BigDecimal(value));
    }
}
