package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public  final class TSC_SuplaChannelPack  implements Proto {
    public final int count;
    public final int totalLeft;
    public final TSC_SuplaChannel[] channels;

    public TSC_SuplaChannelPack(int count, int totalLeft, TSC_SuplaChannel[] channels) {
        this.count = count;
        this.totalLeft = totalLeft;
        this.channels = channels;
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
