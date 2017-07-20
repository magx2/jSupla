package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

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
    public String toString() {
        return "TSDC_SuplaSetActivityTimeoutResult{" +
                "activityTimeout=" + activityTimeout +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
