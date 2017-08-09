package pl.grzeslowski.jsupla.protocol.structs;


import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

/**
 * This structure is send through the wire.
 * <p>
 * <pre>
 * {@code
 * typedef struct {
 * char tag[SUPLA_TAG_SIZE];
 * unsigned char version;
 * unsigned _supla_int_t rr_id; // Request/Response ID
 * unsigned _supla_int_t call_type;
 * unsigned _supla_int_t data_size;
 * char data[SUPLA_MAX_DATA_SIZE]; // Last variable in struct!
 * }TSuplaDataPacket;
 * }
 * </pre>
 */
public final class TSuplaDataPacket implements Proto {
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

    public TSuplaDataPacket(short version, long rrId, long callType, long dataSize, byte[] data) {
        this.version = version;
        this.rrId = rrId;
        this.callType = callType;
        this.dataSize = dataSize;
        this.data = Preconditions.size(data, 0, SUPLA_MAX_DATA_SIZE);
    }

    @Override
    public int size() {
        return MIN_SIZE + data.length;
    }

    @Override
    public String toString() {
        return "TSuplaDataPacket{" +
                "version=" + version +
                ", rrId=" + rrId +
                ", callType=" + callType +
                ", dataSize=" + dataSize +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
