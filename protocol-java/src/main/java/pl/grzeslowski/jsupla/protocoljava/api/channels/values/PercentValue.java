package pl.grzeslowski.jsupla.protocoljava.api.channels.values;

import static pl.grzeslowski.jsupla.Preconditions.size;

public final class PercentValue implements ChannelValue {
    @SuppressWarnings("unused") public static final PercentValue ZERO = new PercentValue(0);
    @SuppressWarnings("unused") public static final PercentValue HUNDRED = new PercentValue(100);

    private final int value;

    @SuppressWarnings("WeakerAccess")
    public PercentValue(final int value) {
        this.value = size(value, 0, 100);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PercentValue)) {
            return false;
        }

        final PercentValue that = (PercentValue) o;

        return value == that.value;
    }

    @Override
    public final int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "PercentValue{" +
                       "value=" + value + "%" +
                       '}';
    }
}
