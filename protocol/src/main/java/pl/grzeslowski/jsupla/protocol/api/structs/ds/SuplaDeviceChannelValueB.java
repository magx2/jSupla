package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED_B;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

/**
 * @since v12.0
 */
@EqualsAndHashCode
@ToString
public class SuplaDeviceChannelValueB implements DeviceServer {
    public static final int SIZE = BYTE_SIZE * 2 + SUPLA_CHANNELVALUE_SIZE;

    /**
     * unsigned char
     */
    public final short channelNumber;
    /**
     * unsigned char
     */
    public final short offline;
    public final byte[] value;

    public SuplaDeviceChannelValueB(short channelNumber, short offline, byte[] value) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.offline = unsignedByteSize(offline);
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
        ;
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED_B;
    }

    @Override
    public int size() {
        return SIZE;
    }
}
