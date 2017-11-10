package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import static pl.grzeslowski.jsupla.Preconditions.id;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterClientResult implements ServerClient {
    public static final int SIZE = BYTE_SIZE * 3 + INT_SIZE * 4;
    public final int resultCode;
    public final int clientId;
    public final int locationCount;
    public final int channelCount;
    /**
     * unsigned.
     */
    public final short activityTimeout;
    /**
     * unsigned.
     */
    public final short version;
    /**
     * unsigned.
     */
    public final short versionMin;

    public SuplaRegisterClientResult(int resultCode,
                                     int clientId,
                                     int locationCount,
                                     int channelCount,
                                     short activityTimeout,
                                     short version,
                                     short versionMin) {
        this.resultCode = resultCode;
        this.clientId = id(clientId);
        this.locationCount = positiveOrZero(locationCount);
        this.channelCount = positiveOrZero(channelCount);
        this.activityTimeout = positiveOrZero(activityTimeout);
        this.version = positiveOrZero(version);
        this.versionMin = positiveOrZero(versionMin);
        sizeMax(versionMin, version);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SuplaRegisterClientResult)) return false;

        final SuplaRegisterClientResult that = (SuplaRegisterClientResult) o;

        if (resultCode != that.resultCode) return false;
        if (clientId != that.clientId) return false;
        if (locationCount != that.locationCount) return false;
        if (channelCount != that.channelCount) return false;
        if (activityTimeout != that.activityTimeout) return false;
        if (version != that.version) return false;
        return versionMin == that.versionMin;
    }

    @Override
    public int hashCode() {
        int result = resultCode;
        result = 31 * result + clientId;
        result = 31 * result + locationCount;
        result = 31 * result + channelCount;
        result = 31 * result + (int) activityTimeout;
        result = 31 * result + (int) version;
        result = 31 * result + (int) versionMin;
        return result;
    }

    @Override
    public String toString() {
        return "SuplaRegisterClientResult{" +
                "resultCode=" + resultCode +
                ", clientId=" + clientId +
                ", locationCount=" + locationCount +
                ", channelCount=" + channelCount +
                ", activityTimeout=" + activityTimeout +
                ", version=" + version +
                ", versionMin=" + versionMin +
                '}';
    }
}
