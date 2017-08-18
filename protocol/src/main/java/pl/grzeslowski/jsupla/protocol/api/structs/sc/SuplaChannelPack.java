package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;

import static java.lang.String.format;
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
        this.count = count;
        this.totalLeft = totalLeft;
        if (count > SUPLA_CHANNELPACK_MAXSIZE) {
            throw new IllegalArgumentException(format(
                    "count (%s) is bigger that SUPLA_CHANNELPACK_MAXSIZE (%s)", count, SUPLA_CHANNELPACK_MAXSIZE
            ));
        }
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
    public String toString() {
        return "SuplaChannelPack{" +
                "count=" + count +
                ", totalLeft=" + totalLeft +
                ", channels=" + Arrays.toString(channels) +
                '}';
    }
}
