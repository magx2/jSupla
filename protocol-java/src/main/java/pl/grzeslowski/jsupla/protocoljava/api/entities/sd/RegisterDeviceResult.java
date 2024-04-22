package pl.grzeslowski.jsupla.protocoljava.api.entities.sd;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import static pl.grzeslowski.jsupla.Preconditions.*;

public class RegisterDeviceResult implements ServerDeviceEntity {
    private final int resultCode;
    @PositiveOrZero
    @Max(Byte.MAX_VALUE)
    private final int activityTimeout;
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int version;
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int versionMin;

    public RegisterDeviceResult(final int resultCode,
                                @PositiveOrZero @Max(Byte.MAX_VALUE) final int activityTimeout,
                                @Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int version,
                                @Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int versionMin) {
        this.resultCode = resultCode;
        this.activityTimeout = positiveOrZero(byteSize(activityTimeout));
        this.version = byteSize(version);
        this.versionMin = byteSize(versionMin);
        min(version, versionMin);
    }

    public int getResultCode() {
        return resultCode;
    }

    public int getActivityTimeout() {
        return activityTimeout;
    }

    public int getVersion() {
        return version;
    }

    public int getVersionMin() {
        return versionMin;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterDeviceResult)) {
            return false;
        }

        final RegisterDeviceResult that = (RegisterDeviceResult) o;

        if (resultCode != that.resultCode) {
            return false;
        }
        if (activityTimeout != that.activityTimeout) {
            return false;
        }
        if (version != that.version) {
            return false;
        }
        return versionMin == that.versionMin;
    }

    @Override
    public final int hashCode() {
        int result = resultCode;
        result = 31 * result + activityTimeout;
        result = 31 * result + version;
        result = 31 * result + versionMin;
        return result;
    }

    @Override
    public String toString() {
        return "RegisterDeviceResult{" +
            "resultCode=" + resultCode +
            ", activityTimeout=" + activityTimeout +
            ", version=" + version +
            ", versionMin=" + versionMin +
            '}';
    }
}
