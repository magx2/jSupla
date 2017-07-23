package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;

public final class TSDC_SuplaVersionError  implements Proto {
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
    public int size() {
        return BYTE_SIZE *2;
    }

    @Override
    public String toString() {
        return "TSDC_SuplaVersionError{" +
                "serverVersionMin=" + serverVersionMin +
                ", serverVersion=" + serverVersion +
                '}';
    }
}
