package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType;

import static pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class FirmwareUpdateUrlResult implements ServerDevice {
    public final byte exists;
    public final FirmwareUpdateUrl url;

    public FirmwareUpdateUrlResult(byte exists, FirmwareUpdateUrl url) {
        this.exists = exists;
        this.url = url;
    }


    @Override
    public ServerDeviceCallType callType() {
        return SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT;
    }

    @Override
    public int size() {
        assert url != null;
        return BYTE_SIZE + url.size();
    }

    @Override
    public String toString() {
        return "FirmwareUpdateUrlResult{" +
                "exists=" + exists +
                ", url=" + url +
                '}';
    }
}
