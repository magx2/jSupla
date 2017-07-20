package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

public final class TDCS_SuplaSetActivityTimeout implements Proto {
    /**
     * unsigned
     */
    public final byte activityTimeout;

    public TDCS_SuplaSetActivityTimeout(byte activityTimeout) {
        this.activityTimeout = activityTimeout;
    }

    @Override
    public String toString() {
        return "TDCS_SuplaSetActivityTimeout{" +
                "activityTimeout=" + activityTimeout +
                '}';
    }
}
