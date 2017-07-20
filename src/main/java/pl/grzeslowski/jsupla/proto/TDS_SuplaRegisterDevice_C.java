package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

/**
 * @since ver 6
 */
public final class TDS_SuplaRegisterDevice_C implements Proto  {
    public final int locationId;
    /**
     * UTF-8
     */
    public final byte[] locationPwd;
    public final byte[] guid;
    /**
     * UTF-8
     */
    public final byte[] name;
    public final byte[] softVer;
    public final byte[] serverName;
    /**
     * unsigned
     */
    public final byte chennelCount;
    public final TDS_SuplaDeviceChannel_B[] channels;

    public TDS_SuplaRegisterDevice_C(int locationId, byte[] locationPwd, byte[] guid, byte[] name, byte[] softVer, byte[] serverName, byte chennelCount, TDS_SuplaDeviceChannel_B[] channels) {
        this.locationId = locationId;
        this.locationPwd = locationPwd;
        this.guid = guid;
        this.name = name;
        this.softVer = softVer;
        this.serverName = serverName;
        this.chennelCount = chennelCount;
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "TDS_SuplaRegisterDevice_C{" +
                "locationId=" + locationId +
                ", locationPwd=" + Arrays.toString(locationPwd) +
                ", guid=" + Arrays.toString(guid) +
                ", name=" + Arrays.toString(name) +
                ", softVer=" + Arrays.toString(softVer) +
                ", serverName=" + Arrays.toString(serverName) +
                ", chennelCount=" + chennelCount +
                ", channels=" + Arrays.toString(channels) +
                '}';
    }
}
