package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public final class TDS_SuplaRegisterDevice {
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
    /**
     * unsigned
     */
    public final byte channelCount;
    public final  TDS_SuplaDeviceChannel[] channels;

    public TDS_SuplaRegisterDevice(int locationId, byte[] locationPwd, byte[] guid, byte[] name, byte[] softVer, byte channelCount, TDS_SuplaDeviceChannel[] channels) {
        this.locationId = locationId;
        this.locationPwd = locationPwd;
        this.guid = guid;
        this.name = name;
        this.softVer = softVer;
        this.channelCount = channelCount;
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "TDS_SuplaRegisterDevice{" +
                "locationId=" + locationId +
                ", locationPwd=" + Arrays.toString(locationPwd) +
                ", guid=" + Arrays.toString(guid) +
                ", name=" + Arrays.toString(name) +
                ", softVer=" + Arrays.toString(softVer) +
                ", channelCount=" + channelCount +
                ", channels=" + Arrays.toString(channels) +
                '}';
    }
}
