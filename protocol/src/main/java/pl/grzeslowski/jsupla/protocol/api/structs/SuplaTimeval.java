package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaTimeval implements ProtoWithSize {
    public static final int SIZE = INT_SIZE * 2;

    public final long seconds;
    public final long milliseconds;

    public SuplaTimeval(final long seconds, final long milliseconds) {
        this.seconds = positiveOrZero(seconds);
        this.milliseconds = positiveOrZero(milliseconds);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SuplaTimeval)) return false;

        final SuplaTimeval that = (SuplaTimeval) o;

        if (seconds != that.seconds) return false;
        return milliseconds == that.milliseconds;
    }

    @Override
    public int hashCode() {
        int result = (int) (seconds ^ (seconds >>> 32));
        result = 31 * result + (int) (milliseconds ^ (milliseconds >>> 32));
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
