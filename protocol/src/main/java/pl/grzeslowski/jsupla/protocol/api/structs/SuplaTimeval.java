package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.positiveOrZero;

public final class SuplaTimeval implements ProtoWithSize {
    public static final int SIZE = INT_SIZE * 2;

    public final int seconds;
    public final int milliseconds;

    public SuplaTimeval(final int seconds, final int milliseconds) {
        this.seconds = positiveOrZero(seconds);
        this.milliseconds = positiveOrZero(milliseconds);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaTimeval)) {
            return false;
        }

        final SuplaTimeval that = (SuplaTimeval) o;

        if (seconds != that.seconds) {
            return false;
        }
        return milliseconds == that.milliseconds;
    }

    @Override
    public final int hashCode() {
        int result = seconds;
        result = 31 * result + milliseconds;
        return result;
    }

    @Override
    public String toString() {
        return "SuplaTimeval{" +
            "seconds=" + seconds +
            ", milliseconds=" + milliseconds +
            '}';
    }
}
