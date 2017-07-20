package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

/**
 * @since ver 2
 */
public  final class TDS_SuplaDeviceChannel_B  implements Proto {
    /**
     * unsigned
     */
    public final byte number;
    public final int type;
    public final int funcList;
    /**
     * This field name should be default, but this is keyword in Java
     */
    public final int default_;
    public final byte[] value;

    public TDS_SuplaDeviceChannel_B(byte number, int type, int funcList, int default_, byte[] value) {
        this.number = number;
        this.type = type;
        this.funcList = funcList;
        this.default_ = default_;
        this.value = value;
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
