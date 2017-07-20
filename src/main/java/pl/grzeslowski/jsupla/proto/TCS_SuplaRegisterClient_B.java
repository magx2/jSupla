package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

/**
 * @since ver 6
 */
public class TCS_SuplaRegisterClient_B {
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
    public final byte[] serverName;

    public TCS_SuplaRegisterClient_B(int accessId, byte[] accessIdPwd, byte[] guid, byte[] name, byte[] softVer, byte[] serverName) {
        this.accessId = accessId;
        this.accessIdPwd = accessIdPwd;
        this.guid = guid;
        this.name = name;
        this.softVer = softVer;
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        return "TCS_SuplaRegisterClient_B{" +
                "accessId=" + accessId +
                ", accessIdPwd=" + Arrays.toString(accessIdPwd) +
                ", guid=" + Arrays.toString(guid) +
                ", name=" + Arrays.toString(name) +
                ", softVer=" + Arrays.toString(softVer) +
                ", serverName=" + Arrays.toString(serverName) +
                '}';
    }
}
