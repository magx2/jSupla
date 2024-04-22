package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELPACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELPACK_MAXSIZE;

public final class SuplaChannelPack implements ServerClient {
    public static final int MIN_SIZE = INT_SIZE * 2;

    public final int count;
    public final int totalLeft;
    public final SuplaChannel[] channels;

    public SuplaChannelPack(int count, int totalLeft, SuplaChannel[] channels) {
        this.count = Preconditions.max(count, SUPLA_CHANNELPACK_MAXSIZE);
        this.totalLeft = totalLeft;
        this.channels = checkArrayLength(channels, count);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNELPACK_UPDATE;
    }

    @Override
    public int size() {
        return MIN_SIZE + channelsSize();
    }

    private int channelsSize() {
        return Arrays.stream(channels)
            .mapToInt(SuplaChannel::size)
            .sum();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaChannelPack)) {
            return false;
        }

        final SuplaChannelPack that = (SuplaChannelPack) o;

        if (count != that.count) {
            return false;
        }
        if (totalLeft != that.totalLeft) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(channels, that.channels);
    }

    @Override
    public final int hashCode() {
        int result = count;
        result = 31 * result + totalLeft;
        result = 31 * result + Arrays.hashCode(channels);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaChannelPack{" +
            "count=" + count +
            ", totalLeft=" + totalLeft +
            ", channels=" + Arrays.toString(channels) +
            '}';
    }
}
