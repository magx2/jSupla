package pl.grzeslowski.jsupla.protocoljava.api.channels.values;

import java.math.BigDecimal;

public final class DecimalValue implements ChannelValue {
    public final BigDecimal value;

    @SuppressWarnings("WeakerAccess")
    public DecimalValue(final BigDecimal value) {
        this.value = value;
    }

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DecimalValue)) {
            return false;
        }

        final DecimalValue that = (DecimalValue) o;

        return value.equals(that.value);
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "DecimalValue{" +
            "value=" + value +
            '}';
    }
}
