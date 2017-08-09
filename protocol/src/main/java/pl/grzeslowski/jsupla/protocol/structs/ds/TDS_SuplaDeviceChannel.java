package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public final class TDS_SuplaDeviceChannel implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_CHANNELVALUE_SIZE;

    /**
     * unsigned
     */
    public final byte number;
    public final int type;
    public final byte[] value;

    public TDS_SuplaDeviceChannel(byte number, int type, byte[] value) {
        this.number = number;
        this.type = type;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }


    @Override
    public DeviceServerCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "TDS_SuplaDeviceChannel{" +
                "number=" + number +
                ", type=" + type +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
