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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaChannelValue)) {
            return false;
        }

        final SuplaChannelValue that = (SuplaChannelValue) o;

        if (!Arrays.equals(value, that.value)) {
            return false;
        }
        return Arrays.equals(subValue, that.subValue);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(value);
        result = 31 * result + Arrays.hashCode(subValue);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaChannelValue{" +
                "value=" + Arrays.toString(value) +
                ", subValue=" + Arrays.toString(subValue) +
                '}';
    }
}
