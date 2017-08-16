package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class FirmwareUpdateParams implements DeviceServer {
    public final byte platform;
    public final int param1;
    public final int param2;
    public final int param3;
    public final int param4;

    public FirmwareUpdateParams(byte platform, int param1, int param2, int param3, int param4) {
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
        return BYTE_SIZE + INT_SIZE * 4;
    }

    @Override
    public String toString() {
        return "FirmwareUpdateParams{" +
                "platform=" + platform +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                '}';
    }
}
