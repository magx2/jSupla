package pl.grzeslowski.jsupla.protocol.api.structs;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unsigned;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

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
public record SuplaDataPacket(short version, long rrId, long callId, long dataSize, byte[] data)
        implements ProtoWithSize {
    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public static final int MIN_SIZE = BYTE_SIZE + INT_SIZE * 3;

    public SuplaDataPacket {
        version = unsigned(version);
        rrId = unsigned(rrId);
        callId = unsigned(callId);
        dataSize = unsigned(dataSize);
        data = checkArrayLength(data, (int) dataSize);
    }

    /* no call type */

    @Override
    public int protoSize() {
        return CHAR_SIZE // version
                + INT_SIZE // rrId
                + INT_SIZE // callId
                + INT_SIZE // dataSize
                + (int) dataSize * BYTE_SIZE // data
        ;
    }
}
