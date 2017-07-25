package pl.grzeslowski.jsupla.proto.structs;


import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.proto.ProtoPreconditions;

import java.util.Arrays;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;
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
        if (data.length > SUPLA_MAX_DATA_SIZE) {
            throw new IllegalArgumentException(format("data.length %s is bigger than SUPLA_MAX_DATA_SIZE %s", data.length, SUPLA_MAX_DATA_SIZE));
        }
        this.data = ProtoPreconditions.checkArrayLength(data, dataSize - MIN_VALUE);
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
