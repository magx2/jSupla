package pl.grzeslowski.jsupla.protocol.api.structs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

/**
 * This structure is send through the wire.
 */
@EqualsAndHashCode
@ToString
public final class SuplaDataPacket implements ProtoWithSize {
    public static final int MIN_SIZE = BYTE_SIZE + INT_SIZE * 3;
    /**
     * unsigned byte.
     */
    public final short version;
    /**
     * unsigned int.
     */
    public final long rrId;
    /**
     * unsigned int.
     */
    public final long callType;
    /**
     * unsigned int.
     */
    public final long dataSize;
    public final byte[] data;

    public SuplaDataPacket(short version, long rrId, long callType, long dataSize, byte[] data) {
        this.version = version;
        this.rrId = rrId;
        this.callType = callType;
        this.dataSize = Preconditions.max(dataSize, SUPLA_MAX_DATA_SIZE);
        this.data = checkArrayLength(data, (int) dataSize);
    }

    public SuplaDataPacket(final short version, final long rrId, final long callType, final byte[] data) {
        this(version, rrId, callType, data.length, data);
    }

    @Override
    public int size() {
        return MIN_SIZE + data.length;
    }
}
