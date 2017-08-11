package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class SuplaSetActivityTimeoutResult implements ServerClientDevice {
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
        this.min = min;
        this.max = max;
        if (min > max) {
            throw new IllegalArgumentException(format("min (%s) need to be smaller than max (%s)!", min, max));
        }
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
    public String toString() {
        return "SuplaSetActivityTimeoutResult{" +
                "activityTimeout=" + activityTimeout +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
