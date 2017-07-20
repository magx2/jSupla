package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public  final class TSDC_SuplaGetVersionResult  implements Proto {
    /**
     * unsigned
     */
    public final byte protoVersionMin;
    /**
     * unsigned
     */
    public final byte protoVersion;
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
