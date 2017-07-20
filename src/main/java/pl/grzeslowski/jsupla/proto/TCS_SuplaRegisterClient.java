package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public final  class TCS_SuplaRegisterClient {
    public final int accessId;
    /**
     * UTF-8
     */
    public final byte[] accessIdPwd;
    public final byte[] guid;
    /**
     * UTF-8
     */
    public final byte[] name;
    public final byte[] softVer;

    public TCS_SuplaRegisterClient(int accessId, byte[] accessIdPwd, byte[] guid, byte[] name, byte[] softVer) {
        this.accessId = accessId;
        this.accessIdPwd = accessIdPwd;
        this.guid = guid;
        this.name = name;
        this.softVer = softVer;
    }

    @Override
    public String toString() {
        return "TCS_SuplaRegisterClient{" +
                "accessId=" + accessId +
                ", accessIdPwd=" + Arrays.toString(accessIdPwd) +
                ", guid=" + Arrays.toString(guid) +
                ", name=" + Arrays.toString(name) +
                ", softVer=" + Arrays.toString(softVer) +
                '}';
    }
}
