package pl.grzeslowski.jsupla.protocol.api.structs.sd;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_REGISTER_DEVICE_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterDeviceResult implements ServerDevice {
    public static final int SIZE = BYTE_SIZE * 3 + INT_SIZE;

    public final int resultCode;
    public final byte activityTimeout;
    public final byte version;
    public final byte versionMin;

    public SuplaRegisterDeviceResult(int resultCode, byte activityTimeout, byte version, byte versionMin) {
        this.resultCode = resultCode;
        this.activityTimeout = positiveOrZero(activityTimeout);
        this.version = version;
        this.versionMin = versionMin;
        min(version, versionMin);
    }

    @Override
    public ServerDeviceCallType callType() {
        return SUPLA_SD_CALL_REGISTER_DEVICE_RESULT;
    }

    @Override
    public int size() {
        return SIZE;
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
