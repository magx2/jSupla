package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import static pl.grzeslowski.jsupla.Preconditions.min;
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
        this.id = min(id, 1);
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaChannelValue)) {
            return false;
        }

        final SuplaChannelValue that = (SuplaChannelValue) o;

        if (eol != that.eol) {
            return false;
        }
        if (id != that.id) {
            return false;
        }
        if (online != that.online) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public final int hashCode() {
        int result = (int) eol;
        result = 31 * result + id;
        result = 31 * result + (int) online;
        result = 31 * result + value.hashCode();
        return result;
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
