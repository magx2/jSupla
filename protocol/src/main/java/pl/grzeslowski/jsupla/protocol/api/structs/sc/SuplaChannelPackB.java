package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELPACK_MAXSIZE;

/**
 * @since ver. 8
 */
public final class SuplaChannelPackB implements ServerClient {
    public static final int MIN_SIZE = INT_SIZE * 2;

    public final int count;
    public final int totalLeft;
    public final SuplaChannelB[] channels;

    public SuplaChannelPackB(final int count, final int totalLeft, final SuplaChannelB[] channels) {
        this.count = max(count, SUPLA_CHANNELPACK_MAXSIZE);
        this.totalLeft = totalLeft;
        this.channels = checkArrayLength(channels, count);
    }

    @Override
    public ServerClientCallType callType() {
        return null;
    }

    @Override
    public int size() {
        return MIN_SIZE + channelsSize();
    }

    private int channelsSize() {
        return Arrays.stream(channels)
                       .mapToInt(SuplaChannelB::size)
                       .sum();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SuplaChannelPackB that = (SuplaChannelPackB) o;
        return count == that.count &&
                       totalLeft == that.totalLeft &&
                       Arrays.equals(channels, that.channels);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(count, totalLeft);
        result = 31 * result + Arrays.hashCode(channels);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaChannelPackB{" +
                       "count=" + count +
                       ", totalLeft=" + totalLeft +
                       ", channels=" + Arrays.toString(channels) +
                       '}';
    }
}
