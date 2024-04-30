package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.Getter;
import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.traits.RegisterLocationDeviceTrait;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_C;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

/**
 * @since ver 6.
 */
public final class SuplaRegisterDeviceC implements DeviceServer, RegisterLocationDeviceTrait {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_LOCATION_PWD_MAXSIZE + SUPLA_GUID_SIZE
        + SUPLA_DEVICE_NAME_MAXSIZE + SUPLA_SOFTVER_MAXSIZE
        + SUPLA_SERVER_NAME_MAXSIZE
        + SuplaDeviceChannelB.SIZE * SUPLA_CHANNELMAXCOUNT;
    @Getter
    public final int locationId;
    /**
     * UTF-8.
     */
    @Getter
    public final byte[] locationPwd;
    @Getter
    public final byte[] guid;
    /**
     * UTF-8.
     */
    @Getter
    public final byte[] name;
    @Getter
    public final byte[] softVer;
    public final byte[] serverName;
    /**
     * unsigned.
     */
    public final short channelCount;
    @Getter
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
        this.channelCount = Preconditions.sizeMax(channelCount, (short) SUPLA_CHANNELMAXCOUNT);
        this.channels = Preconditions.checkArrayLength(channels, channelCount);
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaRegisterDeviceC)) {
            return false;
        }

        final SuplaRegisterDeviceC that = (SuplaRegisterDeviceC) o;

        if (locationId != that.locationId) {
            return false;
        }
        if (channelCount != that.channelCount) {
            return false;
        }
        if (!Arrays.equals(locationPwd, that.locationPwd)) {
            return false;
        }
        if (!Arrays.equals(guid, that.guid)) {
            return false;
        }
        if (!Arrays.equals(name, that.name)) {
            return false;
        }
        if (!Arrays.equals(softVer, that.softVer)) {
            return false;
        }
        if (!Arrays.equals(serverName, that.serverName)) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(channels, that.channels);
    }

    @Override
    public final int hashCode() {
        int result = locationId;
        result = 31 * result + Arrays.hashCode(locationPwd);
        result = 31 * result + Arrays.hashCode(guid);
        result = 31 * result + Arrays.hashCode(name);
        result = 31 * result + Arrays.hashCode(softVer);
        result = 31 * result + Arrays.hashCode(serverName);
        result = 31 * result + (int) channelCount;
        result = 31 * result + Arrays.hashCode(channels);
        return result;
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
