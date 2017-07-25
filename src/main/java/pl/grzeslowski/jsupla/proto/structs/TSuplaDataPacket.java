package pl.grzeslowski.jsupla.proto.structs;


import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_MAX_DATA_SIZE;

public final class TSuplaDataPacket implements Proto {
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
        this.data = Preconditions.size(data, 0, SUPLA_MAX_DATA_SIZE);
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE * 3 + data.length;
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
