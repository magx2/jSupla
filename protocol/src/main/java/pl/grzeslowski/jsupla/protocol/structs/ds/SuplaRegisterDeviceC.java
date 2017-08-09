package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

/**
 * @since ver 6.
 */
public final class SuplaRegisterDeviceC implements DeviceServer {
    public final int locationId;
    /**
     * UTF-8.
     */
    public final byte[] locationPwd;
    public final byte[] guid;
    /**
     * UTF-8.
     */
    public final byte[] name;
    public final byte[] softVer;
    public final byte[] serverName;
    /**
     * unsigned.
     */
    public final byte channelCount;
    public final SuplaDeviceChannelB[] channels;

    public SuplaRegisterDeviceC(int locationId,
                                byte[] locationPwd,
                                byte[] guid,
                                byte[] name,
                                byte[] softVer,
                                byte[] serverName,
                                byte channelCount,
                                SuplaDeviceChannelB[] channels) {
        this.locationId = locationId;
        this.locationPwd = checkArrayLength(locationPwd, SUPLA_LOCATION_PWD_MAXSIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
        this.channelCount = channelCount;
        this.channels = Preconditions.size(channels, 0, SUPLA_CHANNELMAXCOUNT);
    }

    @Override
    public DeviceServerCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE + SUPLA_LOCATION_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_DEVICE_NAME_MAXSIZE +
                SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE +
                SuplaDeviceChannelB.SIZE * SUPLA_CHANNELMAXCOUNT;
    }

    @Override
    public String toString() {
        return "SuplaRegisterDeviceC{" +
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