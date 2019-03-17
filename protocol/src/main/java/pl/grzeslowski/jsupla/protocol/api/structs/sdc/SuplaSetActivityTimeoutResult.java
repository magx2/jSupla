package pl.grzeslowski.jsupla.protocol.api.structs.sdc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;

import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public final class SuplaSetActivityTimeoutResult implements ServerDeviceClient {
    /**
     * unsigned.
     */
    public final short activityTimeout;
    /**
     * unsigned.
     */
    public final short min;
    /**
     * unsigned.
     */
    public final short max;

    public SuplaSetActivityTimeoutResult(short activityTimeout, short min, short max) {
        this.activityTimeout = activityTimeout;
        this.min = positiveOrZero(min);
        this.max = positiveOrZero(max);
        max(min, max);
    }

    @Override
    public ServerDeviceClientCallType callType() {
        return SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT;
    }

    @Override
    public int size() {
        return BYTE_SIZE * 3;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaSetActivityTimeoutResult)) {
            return false;
        }

        final SuplaSetActivityTimeoutResult that = (SuplaSetActivityTimeoutResult) o;

        if (activityTimeout != that.activityTimeout) {
            return false;
        }
        if (min != that.min) {
            return false;
        }
        return max == that.max;
    }

    @Override
    public final int hashCode() {
        int result = (int) activityTimeout;
        result = 31 * result + (int) min;
        result = 31 * result + (int) max;
        return result;
    }

    @Override
    public String toString() {
        return "SuplaSetActivityTimeoutResult{" +
                "activityTimeout=" + activityTimeout +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
