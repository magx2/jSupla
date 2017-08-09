package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType;

import static pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_REGISTER_DEVICE_RESULT;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterDeviceResult implements ServerDevice {
    public final int resultCode;
    public final byte activityTimeout;
    public final byte version;
    public final byte versionMin;

    public SuplaRegisterDeviceResult(int resultCode, byte activityTimeout, byte version, byte versionMin) {
        this.resultCode = resultCode;
        this.activityTimeout = activityTimeout;
        this.version = version;
        this.versionMin = versionMin;
    }

    @Override
    public ServerDeviceCallType callType() {
        return SUPLA_SD_CALL_REGISTER_DEVICE_RESULT;
    }

    @Override
    public int size() {
        return BYTE_SIZE * 3 + INT_SIZE;
    }

    @Override
    public String toString() {
        return "SuplaRegisterDeviceResult{" +
                "resultCode=" + resultCode +
                ", activityTimeout=" + activityTimeout +
                ", version=" + version +
                ", versionMin=" + versionMin +
                '}';
    }
}