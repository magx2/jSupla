package pl.grzeslowski.jsupla.proto;


import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

public final class TSuplaDataPacket implements Proto {
    public static final int SIZE = BYTE_SIZE + INT_SIZE * 3;

    public final byte version;
    public final int rrId;
    public final int callType;
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
