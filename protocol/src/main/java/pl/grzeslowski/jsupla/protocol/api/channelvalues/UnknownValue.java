package pl.grzeslowski.jsupla.protocol.api.channelvalues;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public final class UnknownValue implements ChannelValue {
    public final byte[] bytes;
    public final String message;

    public UnknownValue(final byte[] bytes, final String message) {
        this.bytes = requireNonNull(bytes);
        this.message = requireNonNull(message);
    }

    public UnknownValue(final byte[] bytes) {
        this(bytes, "Don't know how to parse those bytes!");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnknownValue)) {
            return false;
        }

        final UnknownValue that = (UnknownValue) o;

        return Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return "UnknownValue{" +
                       "bytes=" + Arrays.toString(bytes) +
                       ", message='" + message + '\'' +
                       '}';
    }
}
