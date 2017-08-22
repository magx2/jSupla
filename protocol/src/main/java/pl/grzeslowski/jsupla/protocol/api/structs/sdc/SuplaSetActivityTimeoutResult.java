package pl.grzeslowski.jsupla.protocol.api.structs.sdc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;

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
        this.min = Preconditions.max(min, max);
        this.max = Preconditions.min(max, min);
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
