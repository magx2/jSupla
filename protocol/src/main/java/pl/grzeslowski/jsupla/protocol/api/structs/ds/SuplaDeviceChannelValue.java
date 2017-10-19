package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

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
        return SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "SuplaDeviceChannelValue{" +
                "channelNumber=" + channelNumber +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
