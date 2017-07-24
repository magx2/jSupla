package pl.grzeslowski.jsupla.server.entities;

import javax.validation.constraints.Min;

public class RegisterDeviceResult implements Entity {
    @Min(0)
    private final int resultCode;
    @Min(0)
    private final int activityTimeout;
    @Min(0)
    private final int version;
    @Min(0)
    private final int versionMin;

    public RegisterDeviceResult(int resultCode, int activityTimeout, int version, int versionMin) {
        this.resultCode = resultCode;
        this.activityTimeout = activityTimeout;
        this.version = version;
        this.versionMin = versionMin;
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
