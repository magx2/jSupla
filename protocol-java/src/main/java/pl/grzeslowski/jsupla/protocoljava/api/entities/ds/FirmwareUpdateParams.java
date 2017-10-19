package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;

public class FirmwareUpdateParams implements DeviceServerEntity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int platform;
    private final int param1;
    private final int param2;
    private final int param3;
    private final int param4;

    public FirmwareUpdateParams(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int platform,
                                final int param1,
                                final int param2,
                                final int param3,
                                final int param4) {
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FirmwareUpdateParams)) {
            return false;
        }

        final FirmwareUpdateParams that = (FirmwareUpdateParams) o;

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
    public int hashCode() {
        int result = platform;
        result = 31 * result + param1;
        result = 31 * result + param2;
        result = 31 * result + param3;
        result = 31 * result + param4;
        return result;
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
