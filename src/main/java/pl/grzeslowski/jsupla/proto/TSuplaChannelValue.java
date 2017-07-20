package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public final class TSuplaChannelValue {
    /**
     * unsigned
     */
    public final byte[] value;
    /**
     * For example sensor value
     *
     * unsigned
     */
    public final byte[] subValue;

    public TSuplaChannelValue(byte[] value, byte[] subValue) {
        this.value = value;
        this.subValue = subValue;
    }

    @Override
    public String toString() {
        return "TSuplaChannelValue{" +
                "value=" + Arrays.toString(value) +
                ", subValue=" + Arrays.toString(subValue) +
                '}';
    }
}
