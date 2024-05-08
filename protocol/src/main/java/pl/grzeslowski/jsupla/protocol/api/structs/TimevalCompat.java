package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.positiveOrZero;

@lombok.EqualsAndHashCode
@lombok.ToString
public class TimevalCompat implements ProtoWithSize {
    public final int seconds;
    public final int milliseconds;

    public TimevalCompat(final int seconds, final int milliseconds) {
        this.seconds = positiveOrZero(seconds);
        this.milliseconds = positiveOrZero(milliseconds);
    }

    @Override
    public int size() {
        return INT_SIZE * 2;
    }

}
