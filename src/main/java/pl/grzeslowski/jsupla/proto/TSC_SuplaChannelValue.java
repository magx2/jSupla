package pl.grzeslowski.jsupla.proto;

public  final class TSC_SuplaChannelValue {
    public final byte eol;
    public final int id;
    public final byte online;
    public final TSuplaChannelValue value;

    public TSC_SuplaChannelValue(byte eol, int id, byte online, TSuplaChannelValue value) {
        this.eol = eol;
        this.id = id;
        this.online = online;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TSC_SuplaChannelValue{" +
                "eol=" + eol +
                ", id=" + id +
                ", online=" + online +
                ", value=" + value +
                '}';
    }
}
