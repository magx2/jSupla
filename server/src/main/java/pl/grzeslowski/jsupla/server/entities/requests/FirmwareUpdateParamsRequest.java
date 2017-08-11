package pl.grzeslowski.jsupla.server.entities.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;

public class FirmwareUpdateParamsRequest implements Request {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int platform;
    private final int param1;
    private final int param2;
    private final int param3;
    private final int param4;

    public FirmwareUpdateParamsRequest(int platform, int param1, int param2, int param3, int param4) {
        this.platform = byteSize(platform);
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
    }

    public int getPlatform() {
        return platform;
    }

    public int getParam1() {
        return param1;
    }

    public int getParam2() {
        return param2;
    }

    public int getParam3() {
        return param3;
    }

    public int getParam4() {
        return param4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FirmwareUpdateParamsRequest)) {
            return false;
        }

        FirmwareUpdateParamsRequest that = (FirmwareUpdateParamsRequest) o;

        return platform == that.platform
                && param1 == that.param1
                && param2 == that.param2
                && param3 == that.param3
                && param4 == that.param4;
    }

    @Override
    public int hashCode() {
        return platform;
    }

    @Override
    public String toString() {
        return "FirmwareUpdateParamsRequest{" +
                "platform=" + platform +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                '}';
    }
}
