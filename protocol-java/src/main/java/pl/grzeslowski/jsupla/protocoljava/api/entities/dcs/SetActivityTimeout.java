package pl.grzeslowski.jsupla.protocoljava.api.entities.dcs;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class SetActivityTimeout implements DeviceClientServerEntity {
    @Positive
    @Max(UNSIGNED_BYTE_MAX)
    private final int activityTimeout;

    public SetActivityTimeout(@Positive @Max(UNSIGNED_BYTE_MAX) final int activityTimeout) {
        this.activityTimeout = unsignedByteSize(min(activityTimeout, 1));
    }

    public int getActivityTimeout() {
        return activityTimeout;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SetActivityTimeout)) {
            return false;
        }

        final SetActivityTimeout that = (SetActivityTimeout) o;

        return activityTimeout == that.activityTimeout;
    }

    @Override
    public int hashCode() {
        return activityTimeout;
    }

    @Override
    public String toString() {
        return "SetActivityTimeout{" +
                       "activityTimeout=" + activityTimeout +
                       '}';
    }
}
