package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELPACK_MAXSIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public final class TSC_SuplaChannelPack implements ServerDevice {
    public final int count;
    public final int totalLeft;
    public final TSC_SuplaChannel[] channels;

    public TSC_SuplaChannelPack(int count, int totalLeft, TSC_SuplaChannel[] channels) {
        this.count = count;
        this.totalLeft = totalLeft;
        this.channels = checkArrayLength(channels, SUPLA_CHANNELPACK_MAXSIZE);
    }

    @Override
    public CallType callType() {
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
