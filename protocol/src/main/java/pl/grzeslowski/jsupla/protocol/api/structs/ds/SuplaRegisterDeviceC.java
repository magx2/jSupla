package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_C;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

/**
 * @since ver 6.
 */
public final class SuplaRegisterDeviceC implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_LOCATION_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_DEVICE_NAME_MAXSIZE +
                   SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE +
                   SuplaDeviceChannelB.SIZE * SUPLA_CHANNELMAXCOUNT;
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
    public final short channelCount;
    public final SuplaDeviceChannelB[] channels;

    public SuplaRegisterDeviceC(int locationId,
                                byte[] locationPwd,
                                byte[] guid,
                                byte[] name,
                                byte[] softVer,
                                byte[] serverName,
                                short channelCount,
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
        return SUPLA_DS_CALL_REGISTER_DEVICE_C;
    }

    @Override
    public int size() {
        return SIZE;
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
