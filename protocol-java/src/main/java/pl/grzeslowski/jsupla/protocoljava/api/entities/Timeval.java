package pl.grzeslowski.jsupla.protocoljava.api.entities;

import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;

public class Timeval implements Entity {
    @PositiveOrZero
    @Min(0) // FIXME random beans
    private final int seconds;
    @PositiveOrZero
    @Min(0) // FIXME random beans
    private final int milliseconds;

    public Timeval(final @PositiveOrZero int seconds,
                   final @PositiveOrZero int milliseconds) {
        this.seconds = positiveOrZero(seconds);
        this.milliseconds = positiveOrZero(milliseconds);
    }

    @PositiveOrZero
    public int getSeconds() {
        return seconds;
    }

    @PositiveOrZero
    public int getMilliseconds() {
        return milliseconds;
    }

    @Override
    public final boolean equals(final Object o) {
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
    public final int hashCode() {
        return seconds;
    }

    @Override
    public String toString() {
        return "Timeval{" +
            "seconds=" + seconds +
            ", milliseconds=" + milliseconds +
            '}';
    }
}
