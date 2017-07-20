package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public class TSDC_SuplaGetVersionResult {
    public final /*u*/ byte protoVersionMin;
    public final /*u*/ byte protoVersion;
    public final byte[] softVer;

    public TSDC_SuplaGetVersionResult(byte protoVersionMin, byte protoVersion, byte[] softVer) {
        this.protoVersionMin = protoVersionMin;
        this.protoVersion = protoVersion;
        this.softVer = softVer;
    }

    @Override
    public String toString() {
        return "TSDC_SuplaGetVersionResult{" +
                "protoVersionMin=" + protoVersionMin +
                ", protoVersion=" + protoVersion +
                ", softVer=" + Arrays.toString(softVer) +
                '}';
    }
}
