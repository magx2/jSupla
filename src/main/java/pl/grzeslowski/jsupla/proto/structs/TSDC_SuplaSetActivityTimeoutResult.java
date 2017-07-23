package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;

public  final class TSDC_SuplaSetActivityTimeoutResult  implements Proto {
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
