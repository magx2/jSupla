package pl.grzeslowski.jsupla.proto;

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
