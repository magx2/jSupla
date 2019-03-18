package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELMAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@Deprecated
public final class SuplaRegisterDevice implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_LOCATION_PWD_MAXSIZE + SUPLA_GUID_SIZE
                                           + SUPLA_DEVICE_NAME_MAXSIZE + SUPLA_SOFTVER_MAXSIZE
                                           + SuplaDeviceChannel.SIZE * SUPLA_CHANNELMAXCOUNT;
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
    /**
     * unsigned.
     */
    public final short channelCount;
    public final SuplaDeviceChannel[] channels;

    public SuplaRegisterDevice(int locationId,
                               byte[] locationPwd,
                               byte[] guid,
                               byte[] name,
                               byte[] softVer,
                               short channelCount,
                               SuplaDeviceChannel[] channels) {
        this.locationId = locationId;
        this.locationPwd = checkArrayLength(locationPwd, SUPLA_LOCATION_PWD_MAXSIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.channelCount = Preconditions.max(channelCount, (short) SUPLA_CHANNELMAXCOUNT);
        this.channels = checkArrayLength(channels, channelCount);
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_REGISTER_DEVICE;
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
        if (!(o instanceof SuplaRegisterDevice)) {
            return false;
        }

        final SuplaRegisterDevice that = (SuplaRegisterDevice) o;

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
        result = 31 * result + (int) channelCount;
        result = 31 * result + Arrays.hashCode(channels);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaRegisterDevice{" +
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
