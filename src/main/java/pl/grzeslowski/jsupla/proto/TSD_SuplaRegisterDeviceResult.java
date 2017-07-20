package pl.grzeslowski.jsupla.proto;

public class TSD_SuplaRegisterDeviceResult {
    public final int resultCode;
    public final byte activityTimeout;
    public final byte version;
    public final byte versionMin;

    public TSD_SuplaRegisterDeviceResult(int resultCode, byte activityTimeout, byte version, byte versionMin) {
        this.resultCode = resultCode;
        this.activityTimeout = activityTimeout;
        this.version = version;
        this.versionMin = versionMin;
    }

    @Override
    public String toString() {
        return "TSD_SuplaRegisterDeviceResult{" +
                "resultCode=" + resultCode +
                ", activityTimeout=" + activityTimeout +
                ", version=" + version +
                ", versionMin=" + versionMin +
                '}';
    }
}
