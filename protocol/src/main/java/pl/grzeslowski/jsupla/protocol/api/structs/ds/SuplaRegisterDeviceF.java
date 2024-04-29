package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.traits.RegisterEmailDeviceTrait;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.positive;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_F;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@EqualsAndHashCode
@ToString
public final class SuplaRegisterDeviceF implements DeviceServer, RegisterEmailDeviceTrait {
    @Getter
    public final byte[] email;
    /**
     * UTF-8.
     */
    @Getter
    public final byte[] authKey;
    @Getter
    public final byte[] guid;
    /**
     * UTF-8.
     */
    @Getter
    public final byte[] name;
    @Getter
    public final byte[] softVer;
    @Getter
    public final byte[] serverName;

    /**
     * SUPLA_DEVICE_FLAG_*
     */
    public final int flags;

    public final short manufacturerID;
    public final short productID;

    /**
     * unsigned.
     */
    public final short channelCount;
    @Getter
    public final SuplaDeviceChannelD[] channels;

    public SuplaRegisterDeviceF(byte[] email, byte[] authKey, byte[] guid, byte[] name, byte[] softVer,
                                byte[] serverName, int flags, short manufacturerID, short productID, short channelCount, SuplaDeviceChannelD[] channels) {
        this.email = checkArrayLength(email, SUPLA_EMAIL_MAXSIZE);
        this.authKey = checkArrayLength(authKey, SUPLA_AUTHKEY_SIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
        this.flags = flags;
        this.manufacturerID = manufacturerID;
        this.productID = productID;
        this.channelCount = positive(channelCount);
        this.channels = checkArrayLength(channels, channelCount);
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_REGISTER_DEVICE_F;
    }

    @Override
    public int size() {
        return BYTE_SIZE * (SUPLA_EMAIL_MAXSIZE + SUPLA_AUTHKEY_SIZE
            + SUPLA_GUID_SIZE + SUPLA_DEVICE_NAME_MAXSIZE
            + SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE) +
            +INT_SIZE //flags
            + SHORT_SIZE * 2 //manufacturerID, productID
            + BYTE_SIZE + channels.length * SuplaDeviceChannelD.SIZE;
    }
}
