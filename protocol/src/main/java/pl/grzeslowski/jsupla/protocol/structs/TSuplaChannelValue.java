package pl.grzeslowski.jsupla.protocol.structs;

import pl.grzeslowski.jsupla.protocol.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class TSuplaChannelValue implements Proto {
    /**
     * unsigned.
     */
    public final byte[] value;
    /**
     * For example sensor value.
     *
     * unsigned
     */
    public final byte[] subValue;

    public TSuplaChannelValue(byte[] value, byte[] subValue) {
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
        this.subValue = checkArrayLength(subValue, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int size() {
        return SUPLA_CHANNELVALUE_SIZE+ SUPLA_CHANNELVALUE_SIZE;
    }

    @Override
    public String toString() {
        return "TSuplaChannelValue{" +
                "value=" + Arrays.toString(value) +
                ", subValue=" + Arrays.toString(subValue) +
                '}';
    }
}
