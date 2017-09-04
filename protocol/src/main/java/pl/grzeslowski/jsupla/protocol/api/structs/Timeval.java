package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class Timeval implements ProtoWithSize {
    public static final int SIZE = INT_SIZE * 2;

    public final int seconds;
    public final int milliseconds;

    public Timeval(final int seconds, final int milliseconds) {
        this.seconds = min(seconds, 0);
        this.milliseconds = min(milliseconds, 0);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "Timeval{" +
                       "seconds=" + seconds +
                       ", milliseconds=" + milliseconds +
                       '}';
    }
}
