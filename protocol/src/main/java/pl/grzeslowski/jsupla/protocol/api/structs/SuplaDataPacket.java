package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

/**
 * This structure is send through the wire.
 */
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaDataPacket)) {
            return false;
        }

        final SuplaDataPacket that = (SuplaDataPacket) o;

        if (version != that.version) {
            return false;
        }
        if (rrId != that.rrId) {
            return false;
        }
        if (callType != that.callType) {
            return false;
        }
        if (dataSize != that.dataSize) {
            return false;
        }
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = (int) version;
        result = 31 * result + (int) (rrId ^ (rrId >>> 32));
        result = 31 * result + (int) (callType ^ (callType >>> 32));
        result = 31 * result + (int) (dataSize ^ (dataSize >>> 32));
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaDataPacket{" +
                "version=" + version +
                ", rrId=" + rrId +
                ", callType=" + callType +
                ", dataSize=" + dataSize +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
