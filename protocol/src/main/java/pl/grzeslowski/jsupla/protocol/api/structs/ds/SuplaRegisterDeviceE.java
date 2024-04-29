
package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.Getter;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.traits.RegisterDeviceTrait;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.unsigned;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;


@lombok.EqualsAndHashCode
@lombok.ToString
public final class SuplaRegisterDeviceE implements DeviceServer, RegisterDeviceTrait {

    /**
     * UTF8
     */
    private final char[] email;
    private final char[] authKey;
    @Getter
    private final char[] guid;
    /**
     * UTF8
     */
    @Getter
    private final char[] name;
    @Getter
    private final char[] softVer;
    private final char[] serverName;
    /**
     * SUPLA_DEVICE_FLAG_*
     */
    private final int flags;
    private final short manufacturerId;
    private final short productId;
    /**
     * unsgigned short
     */
    private final short channelCount;
    /**
     * Last variable in struct!
     */
    @Getter
    private final SuplaDeviceChannelC[] channels;

    public SuplaRegisterDeviceE(char[] email, char[] authKey, char[] guid, char[] name, char[] softVer, char[] serverName, int flags, short manufacturerId, short productId, short channelCount, SuplaDeviceChannelC[] channels) {
        this.email = checkArrayLength(email, SUPLA_EMAIL_MAXSIZE);
        this.authKey = checkArrayLength(authKey, SUPLA_AUTHKEY_SIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
        this.flags = flags;
        this.manufacturerId = manufacturerId;
        this.productId = productId;
        this.channelCount = unsigned(channelCount);
        this.channels = checkArrayLength(channels, channelCount);
    }


    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
    }

    @Override
    public int size() {
        return SUPLA_EMAIL_MAXSIZE * BYTE_SIZE // email
            + SUPLA_AUTHKEY_SIZE * BYTE_SIZE // authKey
            + SUPLA_GUID_SIZE * BYTE_SIZE // guid
            + SUPLA_DEVICE_NAME_MAXSIZE * BYTE_SIZE // name
            + SUPLA_SOFTVER_MAXSIZE * BYTE_SIZE // softVer
            + SUPLA_SERVER_NAME_MAXSIZE * BYTE_SIZE // serverName
            + INT_SIZE // flags
            + SHORT_SIZE // manufacturerId
            + SHORT_SIZE // productId
            + BYTE_SIZE // channelCount
            + channelCount * SuplaDeviceChannelC.SIZE // channels
            ;
    }
}
