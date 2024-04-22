package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaFirmwareUpdateParams implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + INT_SIZE * 4;
    public final byte platform;
    public final int param1;
    public final int param2;
    public final int param3;
    public final int param4;

    public SuplaFirmwareUpdateParams(byte platform, int param1, int param2, int param3, int param4) {
        this.platform = platform;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL;
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
        if (!(o instanceof SuplaFirmwareUpdateParams)) {
            return false;
        }

        final SuplaFirmwareUpdateParams that = (SuplaFirmwareUpdateParams) o;

        if (platform != that.platform) {
            return false;
        }
        if (param1 != that.param1) {
            return false;
        }
        if (param2 != that.param2) {
            return false;
        }
        if (param3 != that.param3) {
            return false;
        }
        return param4 == that.param4;
    }

    @Override
    public final int hashCode() {
        int result = (int) platform;
        result = 31 * result + param1;
        result = 31 * result + param2;
        result = 31 * result + param3;
        result = 31 * result + param4;
        return result;
    }

    @Override
    public String toString() {
        return "SuplaFirmwareUpdateParams{" +
            "platform=" + platform +
            ", param1=" + param1 +
            ", param2=" + param2 +
            ", param3=" + param3 +
            ", param4=" + param4 +
            '}';
    }
}
