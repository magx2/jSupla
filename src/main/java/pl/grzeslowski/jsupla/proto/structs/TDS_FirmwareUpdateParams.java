package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

public final class TDS_FirmwareUpdateParams  implements Proto {
    public final byte platform;
    public final int param1;
    public final int param2;
    public final int param3;
    public final int param4;

    public TDS_FirmwareUpdateParams(byte platform, int param1, int param2, int param3, int param4) {
        this.platform = platform;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE  * 4;
    }

    @Override
    public String toString() {
        return "TDS_FirmwareUpdateParams{" +
                "platform=" + platform +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                '}';
    }
}
