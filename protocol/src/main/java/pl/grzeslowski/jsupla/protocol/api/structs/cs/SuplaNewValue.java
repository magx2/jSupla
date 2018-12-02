package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;

import java.util.Arrays;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_SET_VALUE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

/**
 * @since ver. 9
 */
public final class SuplaNewValue implements ClientServer {
    public static final int SIZE = INT_SIZE + BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE * BYTE_SIZE;
    public final int id;
    public final byte target;
    public final byte[] value;

    public SuplaNewValue(final int id, final byte target, final byte[] value) {
        this.id = id;
        this.target = target;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public ClientServerCallType callType() {
        return SUPLA_CS_CALL_SET_VALUE;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SuplaNewValue that = (SuplaNewValue) o;
        return id == that.id &&
                       target == that.target &&
                       Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, target);
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaNewValue{" +
                       "id=" + id +
                       ", target=" + target +
                       ", value=" + Arrays.toString(value) +
                       '}';
    }
}
