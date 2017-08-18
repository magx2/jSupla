package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelValue implements ServerClient {
    public static final int SIZE = BYTE_SIZE * 2 + INT_SIZE
                                           + pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.SIZE;

    public final byte eol;
    public final int id;
    public final byte online;
    public final pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue value;

    public SuplaChannelValue(byte eol,
                             int id,
                             byte online,
                             pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue value) {
        this.eol = eol;
        this.id = id;
        this.online = online;
        this.value = value;
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE;
    }

    @Override
    public int size() {
        return SIZE;
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
