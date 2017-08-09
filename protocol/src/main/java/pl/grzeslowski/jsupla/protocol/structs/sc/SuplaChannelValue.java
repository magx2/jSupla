package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelValue implements ServerClient {
    public final byte eol;
    public final int id;
    public final byte online;
    public final pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue value;

    public SuplaChannelValue(byte eol, int id, byte online, pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue value) {
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
        return BYTE_SIZE * 2 + INT_SIZE + value.size();
    }

    @Override
    public String toString() {
        return "SuplaChannelValue{" +
                "eol=" + eol +
                ", id=" + id +
                ", online=" + online +
                ", value=" + value +
                '}';
    }
}
