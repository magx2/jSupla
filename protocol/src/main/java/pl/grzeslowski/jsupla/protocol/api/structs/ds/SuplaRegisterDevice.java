package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.traits.RegisterLocationDeviceTrait;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@ToString
@EqualsAndHashCode
public final class SuplaRegisterDevice implements DeviceServer, RegisterLocationDeviceTrait {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_LOCATION_PWD_MAXSIZE + SUPLA_GUID_SIZE
        + SUPLA_DEVICE_NAME_MAXSIZE + SUPLA_SOFTVER_MAXSIZE
        + SuplaDeviceChannel.SIZE * SUPLA_CHANNELMAXCOUNT;
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
    /**
     * unsigned.
     */
    public final short channelCount;
    @Getter
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
}
