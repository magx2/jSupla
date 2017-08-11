package pl.grzeslowski.jsupla.protocol.structs;


import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;

import java.util.Arrays;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

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
        if (data.length != dataSize) {
            throw new IllegalArgumentException(format("data length (%s) is different than given dataSize (%s)!",
                    data.length, dataSize));
        }
        this.data = data;
    }

    @Override
    public int size() {
        return MIN_SIZE + data.length;
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
