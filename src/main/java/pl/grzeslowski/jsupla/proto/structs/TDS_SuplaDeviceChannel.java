package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

@Deprecated
public  final class TDS_SuplaDeviceChannel  implements Proto {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_CHANNELVALUE_SIZE;

    /**
     * unsigned
     */
    public final byte number;
    public final int type;
    public final byte[] value;

    public TDS_SuplaDeviceChannel(byte number, int type, byte[] value) {
        this.number = number;
        this.type = type;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "TDS_SuplaDeviceChannel{" +
                "number=" + number +
                ", type=" + type +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
