package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.call_types.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELPACK_MAXSIZE;

public final class TSC_SuplaChannelPack implements ServerClient {
    public final int count;
    public final int totalLeft;
    public final TSC_SuplaChannel[] channels;

    public TSC_SuplaChannelPack(int count, int totalLeft, TSC_SuplaChannel[] channels) {
        this.count = count;
        this.totalLeft = totalLeft;
        this.channels = checkArrayLength(channels, SUPLA_CHANNELPACK_MAXSIZE);
    }

    @Override
    public ServerClientCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return INT_SIZE * 2 + channelsSize();
    }

    private int channelsSize() {
        return Arrays.stream(channels)
                .mapToInt(TSC_SuplaChannel::size)
                .sum();
    }


    @Override
    public String toString() {
        return "TSC_SuplaChannelPack{" +
                "count=" + count +
                ", totalLeft=" + totalLeft +
                ", channels=" + Arrays.toString(channels) +
                '}';
    }
}
