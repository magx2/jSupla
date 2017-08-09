package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.proto.consts.CallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.proto.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.proto.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class TDS_SuplaDeviceChannelValue implements DeviceServer {
    /**
     * unsigned
     */
    public final byte channelNumber;
    public final byte[] value;

    public TDS_SuplaDeviceChannelValue(byte channelNumber, byte[] value) {
        this.channelNumber = channelNumber;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }


    @Override
    public CallType callType() {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        return BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE;
    }

    @Override
    public String toString() {
        return "TDS_SuplaDeviceChannelValue{" +
                "channelNumber=" + channelNumber +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
