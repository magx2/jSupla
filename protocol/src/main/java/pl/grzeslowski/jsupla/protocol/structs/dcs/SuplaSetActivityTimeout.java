package pl.grzeslowski.jsupla.protocol.structs.dcs;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceClientServerCallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class SuplaSetActivityTimeout implements DeviceClientServer {
    /**
     * unsigned.
     */
    public final short activityTimeout;

    public SuplaSetActivityTimeout(short activityTimeout) {
        this.activityTimeout = activityTimeout;
    }

    @Override
    public DeviceClientServerCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE;
    }

    @Override
    public String toString() {
        return "SuplaSetActivityTimeout{" +
                "activityTimeout=" + activityTimeout +
                '}';
    }
}
