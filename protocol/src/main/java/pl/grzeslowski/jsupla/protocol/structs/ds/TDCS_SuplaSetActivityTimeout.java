package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.consts.CallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class TDCS_SuplaSetActivityTimeout implements DeviceServer {
    /**
     * unsigned
     */
    public final byte activityTimeout;

    public TDCS_SuplaSetActivityTimeout(byte activityTimeout) {
        this.activityTimeout = activityTimeout;
    }


    @Override
    public CallType callType() {
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
