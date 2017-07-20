package pl.grzeslowski.jsupla.proto;

public final class TDCS_SuplaSetActivityTimeout {
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
