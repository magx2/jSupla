package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import lombok.Getter;
import pl.grzeslowski.jsupla.protocol.api.traits.DeviceChannelTrait;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

/**
 * @since ver 2.
 */
public final class SuplaDeviceChannelB implements ProtoWithSize, DeviceChannelTrait {
    public static final int SIZE = BYTE_SIZE + INT_SIZE * 3 + SUPLA_CHANNELVALUE_SIZE;
    /**
     * unsigned byte.
     */
    @Getter
    public final short number;
    @Getter
    public final int type;
    public final int funcList;
    /**
     * This field name should be default, but this is keyword in Java.
     */
    public final int defaultValue;
    @Getter
    public final byte[] value;

    public SuplaDeviceChannelB(short number, int type, int funcList, int defaultValue, byte[] value) {
        this.number = unsignedByteSize(number);
        this.type = type;
        this.funcList = funcList;
        this.defaultValue = defaultValue;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
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
        if (!(o instanceof SuplaDeviceChannelB)) {
            return false;
        }

        final SuplaDeviceChannelB that = (SuplaDeviceChannelB) o;

        if (number != that.number) {
            return false;
        }
        if (type != that.type) {
            return false;
        }
        if (funcList != that.funcList) {
            return false;
        }
        if (defaultValue != that.defaultValue) {
            return false;
        }
        return Arrays.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        int result = (int) number;
        result = 31 * result + type;
        result = 31 * result + funcList;
        result = 31 * result + defaultValue;
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaDeviceChannelB{" +
            "number=" + number +
            ", type=" + type +
            ", funcList=" + funcList +
            ", defaultValue=" + defaultValue +
            ", value=" + Arrays.toString(value) +
            '}';
    }
}
