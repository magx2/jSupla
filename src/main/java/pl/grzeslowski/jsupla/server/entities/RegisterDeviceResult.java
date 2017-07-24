package pl.grzeslowski.jsupla.server.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.Preconditions.size;

public class RegisterDeviceResult implements Entity {
    @Min(0)
    private final int resultCode;
    @Min(0)
    @Max(255)
    private final int activityTimeout;
    @Min(0)
    @Max(255)
    private final int version;
    @Min(0)
    @Max(255)
    private final int versionMin;

    public RegisterDeviceResult(int resultCode, int activityTimeout, int version, int versionMin) {
        this.resultCode = min(resultCode, 0);
        this.activityTimeout = size(activityTimeout, 0, 255);
        this.version = size(version, 0, 255);
        this.versionMin = size(versionMin, 0, 255);
        if (versionMin > version) {
            throw new IllegalArgumentException(format("Min version %s can not be larger that version %s!", versionMin, version));
        }
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
    public String toString() {
        return "RegisterDeviceResult{" +
                "resultCode=" + resultCode +
                ", activityTimeout=" + activityTimeout +
                ", version=" + version +
                ", versionMin=" + versionMin +
                '}';
    }
}
