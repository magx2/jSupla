package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

/**
 * @since ver 2.
 */
public final class TDS_SuplaDeviceChannel_B implements DeviceServer {
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
    public final int default_;
    public final byte[] value;

    public TDS_SuplaDeviceChannel_B(short number, int type, int funcList, int default_, byte[] value) {
        this.number = number;
        this.type = type;
        this.funcList = funcList;
        this.default_ = default_;
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
        return "TDS_SuplaDeviceChannel_B{" +
                "number=" + number +
                ", type=" + type +
                ", funcList=" + funcList +
                ", default_=" + default_ +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
