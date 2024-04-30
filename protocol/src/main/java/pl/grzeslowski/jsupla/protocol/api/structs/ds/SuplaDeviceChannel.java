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

public final class SuplaDeviceChannel implements ProtoWithSize, DeviceChannelTrait {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_CHANNELVALUE_SIZE;

    /**
     * unsigned.
     */
    @Getter
    public final short number;
    @Getter
    public final int type;
    @Getter
    public final byte[] value;

    public SuplaDeviceChannel(short number, int type, byte[] value) {
        this.number = unsignedByteSize(number);
        this.type = type;
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
        if (!(o instanceof SuplaDeviceChannel)) {
            return false;
        }

        final SuplaDeviceChannel that = (SuplaDeviceChannel) o;

        if (number != that.number) {
            return false;
        }
        if (type != that.type) {
            return false;
        }
        return Arrays.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        int result = (int) number;
        result = 31 * result + type;
        result = 31 * result + Arrays.hashCode(value);
        return result;
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
