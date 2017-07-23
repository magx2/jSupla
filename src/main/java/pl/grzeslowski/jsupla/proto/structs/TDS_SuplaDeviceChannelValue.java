package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public  final class TDS_SuplaDeviceChannelValue  implements Proto {
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
    public String toString() {
        return "TDS_SuplaDeviceChannelValue{" +
                "channelNumber=" + channelNumber +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
