package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

/**
 * @since ver 2.
 */
// TODO should be proto with size
public final class SuplaDeviceChannelB implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + INT_SIZE * 3 + SUPLA_CHANNELVALUE_SIZE;
    /**
     * unsigned byte.
     */
    public final short number;
    public final int type;
    public final int funcList;
    /**
     * This field name should be default, but this is keyword in Java.
     */
    public final int defaultValue;
    public final byte[] value;

    public SuplaDeviceChannelB(short number, int type, int funcList, int defaultValue, byte[] value) {
        this.number = number;
        this.type = type;
        this.funcList = funcList;
        this.defaultValue = defaultValue;
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
        return "SuplaDeviceChannelB{" +
                "number=" + number +
                ", type=" + type +
                ", funcList=" + funcList +
                ", defaultValue=" + defaultValue +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
