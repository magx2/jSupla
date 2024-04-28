package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED_C;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

/**
 * @since v12
 */

@ToString
@EqualsAndHashCode
public final class SuplaDeviceChannelValueC implements DeviceServer {
    public static final int SIZE = BYTE_SIZE * 2 + INT_SIZE + SUPLA_CHANNELVALUE_SIZE;

    /**
     * unsigned char
     */
    public final short channelNumber;
    /**
     * unsigned char
     */
    public final short offline;
    /**
     * unsigned int
     */
    public final long validityTimeSec;
    public final byte[] value;

    public SuplaDeviceChannelValueC(short channelNumber, short offline, long validityTimeSec, byte[] value) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.offline = unsignedByteSize(offline);
        this.validityTimeSec = unsignedIntSize(validityTimeSec);
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED_C;
    }

    @Override
    public int size() {
        return SIZE;
    }
}
