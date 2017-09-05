package pl.grzeslowski.jsupla.protocoljava.api.entities.sdc;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class SetActivityTimeoutResult implements ServerDeviceClientEntity {
    @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) private final int activityTimeout;
    @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) private final int min;
    @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) private final int max;

    public SetActivityTimeoutResult(@PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int activityTimeout,
                                    @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int min,
                                    @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int max) {
        this.activityTimeout = unsignedByteSize(activityTimeout);
        this.min = unsignedByteSize(min);
        this.max = unsignedByteSize(max);
        max(min, max);
    }

    public int getActivityTimeout() {
        return activityTimeout;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {return true;}
        if (!(o instanceof SetActivityTimeoutResult)){ return false;}

        final SetActivityTimeoutResult that = (SetActivityTimeoutResult) o;

        if (activityTimeout != that.activityTimeout){ return false;}
        if (min != that.min) {return false;}
        return max == that.max;
    }

    @Override
    public int hashCode() {
        int result = activityTimeout;
        result = 31 * result + min;
        result = 31 * result + max;
        return result;
    }

    @Override
    public String toString() {
        return "SetActivityTimeoutResult{" +
                       "activityTimeout=" + activityTimeout +
                       ", min=" + min +
                       ", max=" + max +
                       '}';
    }
}
