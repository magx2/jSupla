package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.consts.CallTypes;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;

public final class TDCS_SuplaSetActivityTimeout implements DeviceServer {
    /**
     * unsigned
     */
    public final byte activityTimeout;

    public TDCS_SuplaSetActivityTimeout(byte activityTimeout) {
        this.activityTimeout = activityTimeout;
    }


    @Override
    public CallTypes callType() {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        return BYTE_SIZE ;
    }

    @Override
    public String toString() {
        return "TDCS_SuplaSetActivityTimeout{" +
                "activityTimeout=" + activityTimeout +
                '}';
    }
}
