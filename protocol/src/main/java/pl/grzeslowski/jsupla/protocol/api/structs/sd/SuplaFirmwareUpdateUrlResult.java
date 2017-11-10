package pl.grzeslowski.jsupla.protocol.api.structs.sd;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public final class SuplaFirmwareUpdateUrlResult implements ServerDevice {
    public static final int SIZE = BYTE_SIZE + SuplaFirmwareUpdateUrl.SIZE;

    public final byte exists;
    public final SuplaFirmwareUpdateUrl url;

    public SuplaFirmwareUpdateUrlResult(byte exists, SuplaFirmwareUpdateUrl url) {
        this.exists = exists;
        this.url = url;
    }

    @Override
    public ServerDeviceCallType callType() {
        return SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SuplaFirmwareUpdateUrlResult)) return false;

        final SuplaFirmwareUpdateUrlResult that = (SuplaFirmwareUpdateUrlResult) o;

        if (exists != that.exists) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result = (int) exists;
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SuplaFirmwareUpdateUrlResult{" +
                "exists=" + exists +
                ", url=" + url +
                '}';
    }
}
