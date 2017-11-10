package pl.grzeslowski.jsupla.protocol.api.structs.sdc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;

import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_VERSIONERROR;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public final class SuplaVersionError implements ServerDeviceClient {
    public static final int SIZE = BYTE_SIZE * 2;
    /**
     * unsigned.
     */
    public final short serverVersionMin;
    /**
     * unsigned.
     */
    public final short serverVersion;

    public SuplaVersionError(short serverVersionMin, short serverVersion) {
        this.serverVersionMin = max(serverVersionMin, serverVersion);
        this.serverVersion = min(serverVersion, serverVersionMin);
    }

    @Override
    public ServerDeviceClientCallType callType() {
        return SUPLA_SDC_CALL_VERSIONERROR;
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
        if (!(o instanceof SuplaVersionError)) {
            return false;
        }

        final SuplaVersionError that = (SuplaVersionError) o;

        if (serverVersionMin != that.serverVersionMin) {
            return false;
        }
        return serverVersion == that.serverVersion;
    }

    @Override
    public int hashCode() {
        int result = (int) serverVersionMin;
        result = 31 * result + (int) serverVersion;
        return result;
    }

    @Override
    public String toString() {
        return "SuplaVersionError{" +
                       "serverVersionMin=" + serverVersionMin +
                       ", serverVersion=" + serverVersion +
                       '}';
    }
}
