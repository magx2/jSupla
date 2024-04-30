package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@EqualsAndHashCode
@ToString
public final class SuplaDeviceChannelValue implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE;
    /**
     * unsigned.
     */
    public final short channelNumber;
    public final byte[] value;

    public SuplaDeviceChannelValue(short channelNumber, byte[] value) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
    }

    @Override
    public int size() {
        return SIZE;
    }
}
