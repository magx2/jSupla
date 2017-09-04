package pl.grzeslowski.jsupla.protocoljava.api.entities;

import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

public class Timeval implements Entity {
    private final long seconds;
    private final long milliseconds;

    public Timeval(final long seconds, final long milliseconds) {
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public long getSeconds() {
        return seconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Timeval)) {
            return false;
        }

        final Timeval timeval = (Timeval) o;

        if (seconds != timeval.seconds) {
            return false;
        }
        return milliseconds == timeval.milliseconds;
    }

    @Override
    public int hashCode() {
        return (int) (seconds ^ (seconds >>> 32));
    }

    @Override
    public String toString() {
        return "Timeval{" +
                       "seconds=" + seconds +
                       ", milliseconds=" + milliseconds +
                       '}';
    }
}
