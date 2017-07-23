package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

public  final class TSC_SuplaChannelValue implements Proto {
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
    public int size() {
        return BYTE_SIZE*2 + INT_SIZE + value.size();
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
