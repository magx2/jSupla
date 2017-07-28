package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallType;

import static pl.grzeslowski.jsupla.consts.CallType.SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

public final class TSD_SuplaRegisterDeviceResult implements ServerDevice {
    public final int resultCode;
    public final byte activityTimeout;
    public final byte version;
    public final byte versionMin;

    public TSD_SuplaRegisterDeviceResult(int resultCode, byte activityTimeout, byte version, byte versionMin) {
        this.resultCode = resultCode;
        this.activityTimeout = activityTimeout;
        this.version = version;
        this.versionMin = versionMin;
    }

    @Override
    public CallType callType() {
        return SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
    }

    @Override
    public int size() {
        return BYTE_SIZE *3+ INT_SIZE ;
    }

    @Override
    public String toString() {
        return "TSD_SuplaRegisterDeviceResult{" +
                "resultCode=" + resultCode +
                ", activityTimeout=" + activityTimeout +
                ", version=" + version +
                ", versionMin=" + versionMin +
                '}';
    }
}
