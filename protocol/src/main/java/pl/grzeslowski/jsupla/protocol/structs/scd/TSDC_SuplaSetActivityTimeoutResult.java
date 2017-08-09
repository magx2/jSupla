package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class TSDC_SuplaSetActivityTimeoutResult implements ServerClientDevice {
    /**
     * unsigned
     */
    public final byte activityTimeout;
    /**
     * unsigned
     */
    public final byte min;
    /**
     * unsigned
     */
    public final byte max;

    public TSDC_SuplaSetActivityTimeoutResult(byte activityTimeout, byte min, byte max) {
        this.activityTimeout = activityTimeout;
        this.min = min;
        this.max = max;
    }


    @Override
    public ServerDeviceClientCallType callType() {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        return BYTE_SIZE *3;
    }

    @Override
    public String toString() {
        return "TSDC_SuplaSetActivityTimeoutResult{" +
                "activityTimeout=" + activityTimeout +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
