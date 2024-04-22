package pl.grzeslowski.jsupla.protocol.api.structs.dcs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public final class SuplaSetActivityTimeout implements DeviceClientServer {
    public static final int SIZE = BYTE_SIZE;
    /**
     * unsigned.
     */
    public final short activityTimeout;

    public SuplaSetActivityTimeout(short activityTimeout) {
        this.activityTimeout = unsignedByteSize(min(activityTimeout, 1));
    }

    @Override
    public DeviceClientServerCallType callType() {
        return SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaSetActivityTimeout)) {
            return false;
        }

        final SuplaSetActivityTimeout that = (SuplaSetActivityTimeout) o;

        return activityTimeout == that.activityTimeout;
    }

    @Override
    public final int hashCode() {
        return (int) activityTimeout;
    }

    @Override
    public String toString() {
        return "SuplaSetActivityTimeout{" +
            "activityTimeout=" + activityTimeout +
            '}';
    }
}
