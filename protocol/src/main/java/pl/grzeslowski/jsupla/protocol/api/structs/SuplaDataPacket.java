package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unsigned;

/**
 * Original code:
 * <pre>
 * typedef struct {
 * char tag[SUPLA_TAG_SIZE];
 * unsigned char version;
 * unsigned _supla_int_t rr_id;  // Request/Response ID
 * unsigned _supla_int_t call_id;
 * unsigned _supla_int_t data_size;
 * char data[SUPLA_MAX_DATA_SIZE];  // Last variable in struct!
 * } TSuplaDataPacket;
 * </pre>
 */
@lombok.EqualsAndHashCode
@lombok.ToString
public class SuplaDataPacket implements ProtoWithSize {
    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public static final int MIN_SIZE = BYTE_SIZE + INT_SIZE * 3;
    /**
     * unsigned char
     */
    public final short version;
    /**
     * Request/Response ID
     * <p>
     * unsigned _supla_int_t
     */
    public final long rrId;
    /**
     * unsigned _supla_int_t
     */
    public final long callId;
    /**
     * unsigned _supla_int_t
     */
    public final long dataSize;
    /**
     * Last variable in struct!
     */
    public final byte[] data;

    public SuplaDataPacket(short version,
                           long rrId,
                           long callId,
                           long dataSize,
                           byte[] data) {
        this.version = unsigned(version);
        this.rrId = unsigned(rrId);
        this.callId = unsigned(callId);
        this.dataSize = unsigned(dataSize);
        this.data = checkArrayLength(data, (int) dataSize);
    }

    /* no call type */

    @Override
    public int size() {
        return CHAR_SIZE // version
               + INT_SIZE // rrId
               + INT_SIZE // callId
               + INT_SIZE // dataSize
               + (int) dataSize * BYTE_SIZE // data
            ;
    }
}
