package pl.grzeslowski.jsupla.proto.structs;


import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

public final class TSuplaDataPacket implements Proto {
    public static final int SIZE = BYTE_SIZE + INT_SIZE * 3 + (SUPLA_MAX_DATA_SIZE - BYTE_SIZE - INT_SIZE * 3 );

    /**
     * unsigned
     */
    public final byte version;
    /**
     * unsigned
     */
    public final int rrId;
    /**
     * unsigned
     */
    public final int callType;
    /**
     * unsigned
     */
    public final int dataSize;
    public final byte[] data;

    public TSuplaDataPacket(byte version, int rrId, int callType, int dataSize, byte[] data) {
        this.version = version;
        this.rrId = rrId;
        this.callType = callType;
        this.dataSize = dataSize;
        this.data = data;
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
