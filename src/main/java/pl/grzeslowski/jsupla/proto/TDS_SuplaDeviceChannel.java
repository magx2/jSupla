package pl.grzeslowski.jsupla.proto;

public  final class TDS_SuplaDeviceChannel  implements Proto {
    /**
     * unsigned
     */
    public final byte number;
    public final int type;
    public final byte value;

    public TDS_SuplaDeviceChannel(byte number, int type, byte value) {
        this.number = number;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TDS_SuplaDeviceChannel{" +
                "number=" + number +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
