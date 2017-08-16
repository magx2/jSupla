package pl.grzeslowski.jsupla.protocol.api.structs.dcs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

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
        return SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
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