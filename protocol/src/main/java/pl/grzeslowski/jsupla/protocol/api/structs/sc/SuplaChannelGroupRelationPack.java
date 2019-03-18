package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.positive;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since ver. 9
 */
public final class SuplaChannelGroupRelationPack implements ServerClient {
    public static final int MIN_SIZE = INT_SIZE * 2;
    public final int count;
    public final int totalLeft;
    public final SuplaChannelGroupRelation[] items;

    public SuplaChannelGroupRelationPack(final int count,
                                         final int totalLeft,
                                         final SuplaChannelGroupRelation[] items) {
        this.count = positive(count);
        this.totalLeft = positiveOrZero(totalLeft);
        this.items = checkArrayLength(items, count);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE;
    }

    @Override
    public int size() {
        return MIN_SIZE + items.length * SuplaChannelGroupRelation.SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SuplaChannelGroupRelationPack that = (SuplaChannelGroupRelationPack) o;
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
        return "SuplaChannelGroupRelationPack{" +
                       "count=" + count +
                       ", totalLeft=" + totalLeft +
                       ", items=" + Arrays.toString(items) +
                       '}';
    }
}
