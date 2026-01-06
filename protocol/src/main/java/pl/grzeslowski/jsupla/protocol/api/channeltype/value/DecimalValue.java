package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.NonNull;

import java.math.BigDecimal;

/**
 * @param value
 */
public record DecimalValue(@NonNull BigDecimal value) implements ChannelValue {
    public DecimalValue(final int value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final long value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final double value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final float value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final byte value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final short value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final String value) {
        this(new BigDecimal(value));
    }
}
