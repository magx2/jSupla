package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.call_types.ServerClientCallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class TSC_SuplaRegisterClientResult implements ServerClient {
    public final int resultCode;
    public final int clientId;
    public final int locationCount;
    public final int channelCount;
    /**
     * unsigned
     */
    public final byte activityTimeout;
    /**
     * unsigned
     */
    public final byte version;
    /**
     * unsigned
     */
    public final byte versionMin;

    public TSC_SuplaRegisterClientResult(int resultCode, int clientId, int locationCount, int channelCount, byte activityTimeout, byte version, byte versionMin) {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE*3 + INT_SIZE *4;
    }

    @Override
    public String toString() {
        return "TSC_SuplaRegisterClientResult{" +
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
