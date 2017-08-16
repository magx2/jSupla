package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterClientResult implements ServerClient {
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
        this.clientId = clientId;
        this.locationCount = locationCount;
        this.channelCount = channelCount;
        this.activityTimeout = activityTimeout;
        this.version = version;
        this.versionMin = versionMin;
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
    }

    @Override
    public int size() {
        return BYTE_SIZE * 3 + INT_SIZE * 4;
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
