package pl.grzeslowski.jsupla.protocoljava.api.channelvalues;

import static pl.grzeslowski.jsupla.Preconditions.size;

public final class PercentValue implements ChannelValue {
    @SuppressWarnings("unused") public static final PercentValue ZERO = new PercentValue(0);
    @SuppressWarnings("unused") public static final PercentValue HUNDRED = new PercentValue(100);

    public final int value;

    public PercentValue(final int value) {
        this.value = size(value, 0, 100);
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
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "PercentValue{" +
                       "value=" + value + "%" +
                       '}';
    }
}
