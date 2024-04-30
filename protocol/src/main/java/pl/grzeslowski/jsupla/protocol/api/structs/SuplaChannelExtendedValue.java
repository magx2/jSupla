package pl.grzeslowski.jsupla.protocol.api.structs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.unsignedIntSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since ver 10.
 */
@EqualsAndHashCode
@ToString
public final class SuplaChannelExtendedValue implements ProtoWithSize {
    public static final int MIN_SIZE = BYTE_SIZE + INT_SIZE;
    /**
     * EV_TYPE_
     */
    public final byte type;
    /**
     * unsigned
     */
    public final long size;
    public final byte[] value;

    public SuplaChannelExtendedValue(byte type, long size, byte[] value) {
        this.type = type;
        this.size = unsignedIntSize(size);
        this.value = checkArrayLength(value, (int) size);
    }

    @Override
    public int size() {
        return MIN_SIZE + value.length;
    }
}
