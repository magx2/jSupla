package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.call_types.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.structs.TSuplaChannelValue;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class TSC_SuplaChannelValue implements ServerClient {
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
    public ServerClientCallType callType() {
        throw new UnsupportedOperationException();
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