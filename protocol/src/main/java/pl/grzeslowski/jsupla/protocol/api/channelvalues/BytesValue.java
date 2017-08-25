package pl.grzeslowski.jsupla.protocol.api.channelvalues;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public final class BytesValue implements ChannelValue {
    public final byte[] bytes;

    public BytesValue(final byte[] bytes) {
        this.bytes = requireNonNull(bytes);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BytesValue)) {
            return false;
        }

        final BytesValue that = (BytesValue) o;

        return Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return "BytesValue{" +
                       "bytes=" + Arrays.toString(bytes) +
                       '}';
    }
}
