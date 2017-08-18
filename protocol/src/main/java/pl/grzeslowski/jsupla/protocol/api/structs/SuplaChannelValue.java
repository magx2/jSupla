package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaChannelValue implements ProtoWithSize {
    public static final int SIZE = SUPLA_CHANNELVALUE_SIZE + SUPLA_CHANNELVALUE_SIZE;
    /**
     * unsigned.
     */
    public final byte[] value;
    /**
     * For example sensor value.
     *
     * <p>unsigned
     */
    public final byte[] subValue;

    public SuplaChannelValue(byte[] value, byte[] subValue) {
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
        this.subValue = checkArrayLength(subValue, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "SuplaChannelValue{" +
                "value=" + Arrays.toString(value) +
                ", subValue=" + Arrays.toString(subValue) +
                '}';
    }
}
