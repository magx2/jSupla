package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import java.util.Arrays;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.positive;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_D;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

/**
 * @since ver. 7
 */
public final class SuplaRegisterDeviceD implements DeviceServer {
    public final byte[] email;
    /**
     * UTF-8.
     */
    public final byte[] authKey;
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

    public SuplaRegisterDeviceD(byte[] email, byte[] authKey, byte[] guid, byte[] name, byte[] softVer,
                                byte[] serverName, short channelCount, SuplaDeviceChannelB[] channels) {
        this.email = checkArrayLength(email, SUPLA_EMAIL_MAXSIZE);
        this.authKey = checkArrayLength(authKey, SUPLA_AUTHKEY_SIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
        this.channelCount = positive(channelCount);
        this.channels = checkArrayLength(channels, channelCount);
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_REGISTER_DEVICE_D;
    }

    @Override
    public int size() {
        return BYTE_SIZE * (SUPLA_EMAIL_MAXSIZE + SUPLA_AUTHKEY_SIZE
                                    + SUPLA_GUID_SIZE + SUPLA_DEVICE_NAME_MAXSIZE
                                    + SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE) +
                       BYTE_SIZE + channels.length * SuplaDeviceChannelB.SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SuplaRegisterDeviceD that = (SuplaRegisterDeviceD) o;
        return channelCount == that.channelCount &&
                       Arrays.equals(email, that.email) &&
                       Arrays.equals(authKey, that.authKey) &&
                       Arrays.equals(guid, that.guid) &&
                       Arrays.equals(name, that.name) &&
                       Arrays.equals(softVer, that.softVer) &&
                       Arrays.equals(serverName, that.serverName) &&
                       Arrays.equals(channels, that.channels);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(channelCount);
        result = 31 * result + Arrays.hashCode(email);
        result = 31 * result + Arrays.hashCode(authKey);
        result = 31 * result + Arrays.hashCode(guid);
        result = 31 * result + Arrays.hashCode(name);
        result = 31 * result + Arrays.hashCode(softVer);
        result = 31 * result + Arrays.hashCode(serverName);
        result = 31 * result + Arrays.hashCode(channels);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaRegisterDeviceD{" +
                       "email=" + Arrays.toString(email) +
                ", authKey=[PROTECTED]" +
                       ", guid=" + Arrays.toString(guid) +
                       ", name=" + Arrays.toString(name) +
                       ", softVer=" + Arrays.toString(softVer) +
                       ", serverName=" + Arrays.toString(serverName) +
                       ", channelCount=" + channelCount +
                       ", channels=" + Arrays.toString(channels) +
                       '}';
    }
}
