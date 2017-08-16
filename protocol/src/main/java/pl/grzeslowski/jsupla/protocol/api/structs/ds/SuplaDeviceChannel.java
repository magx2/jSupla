package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public final class SuplaDeviceChannel implements ProtoWithSize {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_CHANNELVALUE_SIZE;

    /**
     * unsigned.
     */
    public final short number;
    public final int type;
    public final byte[] value;

    public SuplaDeviceChannel(short number, int type, byte[] value) {
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
        return "SuplaDeviceChannel{" +
                "number=" + number +
                ", type=" + type +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}