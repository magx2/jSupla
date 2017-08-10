package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaDeviceChannelValue implements DeviceServer {
    /**
     * unsigned.
     */
    public final short channelNumber;
    public final byte[] value;

    public SuplaDeviceChannelValue(short channelNumber, byte[] value) {
        this.channelNumber = channelNumber;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }


    @Override
    public DeviceServerCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE;
    }

    @Override
    public String toString() {
        return "SuplaDeviceChannelValue{" +
                "channelNumber=" + channelNumber +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
