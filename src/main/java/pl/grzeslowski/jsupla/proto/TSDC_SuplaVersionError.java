package pl.grzeslowski.jsupla.proto;

public class TSDC_SuplaVersionError {
    /**
     * unsigned
     */
    public final byte serverVersionMin;
    /**
     * unsigned
     */
    public final byte serverVersion;

    public TSDC_SuplaVersionError(byte serverVersionMin, byte serverVersion) {
        this.serverVersionMin = serverVersionMin;
        this.serverVersion = serverVersion;
    }

    @Override
    public String toString() {
        return "TSDC_SuplaVersionError{" +
                "serverVersionMin=" + serverVersionMin +
                ", serverVersion=" + serverVersion +
                '}';
    }
}
