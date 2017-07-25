package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.*;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

/**
 * @since ver 6
 */
public final class TDS_SuplaRegisterDevice_C implements Proto {
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
    public final byte channelCount;
    public final TDS_SuplaDeviceChannel_B[] channels;

    public TDS_SuplaRegisterDevice_C(int locationId, byte[] locationPwd, byte[] guid, byte[] name, byte[] softVer, byte[] serverName, byte channelCount, TDS_SuplaDeviceChannel_B[] channels) {
        this.locationId = locationId;
        this.locationPwd = checkArrayLength(locationPwd, SUPLA_LOCATION_PWD_MAXSIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
        this.channelCount = channelCount;
        this.channels = checkArrayLength(channels, SUPLA_CHANNELMAXCOUNT);
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE + SUPLA_LOCATION_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_DEVICE_NAME_MAXSIZE + SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE + TDS_SuplaDeviceChannel_B.SIZE * SUPLA_CHANNELMAXCOUNT;
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
                ", channelCount=" + channelCount +
                ", channels=" + Arrays.toString(channels) +
                '}';
    }
}
