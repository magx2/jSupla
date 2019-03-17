package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.Preconditions.positive;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_PACK_MAXCOUNT;

/**
 * @since ver. 9
 */
public final class SuplaChannelValuePack implements ServerClient {
    public final int count;
    public final int totalLeft;
    public final SuplaChannelValue[] items;

    public SuplaChannelValuePack(int count, int totalLeft, SuplaChannelValue[] items) {
        this.count = max(positive(count), SUPLA_CHANNELVALUE_PACK_MAXCOUNT);
        this.totalLeft = positiveOrZero(totalLeft);
        this.items = checkArrayLength(items, count);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE;
    }

    @Override
    public int size() {
        return INT_SIZE * 2 + items.length * SuplaChannelValue.SIZE;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuplaChannelValuePack that = (SuplaChannelValuePack) o;
        return count == that.count &&
                       totalLeft == that.totalLeft &&
                       Arrays.equals(items, that.items);
    }

    @Override
    public final int hashCode() {
        int result = Objects.hash(count, totalLeft);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaChannelValuePack{" +
                       "count=" + count +
                       ", totalLeft=" + totalLeft +
                       ", items=" + Arrays.toString(items) +
                       '}';
    }
}
