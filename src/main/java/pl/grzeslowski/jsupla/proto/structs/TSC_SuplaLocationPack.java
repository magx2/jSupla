package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

public  final class TSC_SuplaLocationPack  implements Proto {
    public final int count;
    public final int totalLeft;
    public final TSC_SuplaLocation[] locations;

    public TSC_SuplaLocationPack(int count, int totalLeft, TSC_SuplaLocation[] locations) {
        this.count = count;
        this.totalLeft = totalLeft;
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "TSC_SuplaLocationPack{" +
                "count=" + count +
                ", totalLeft=" + totalLeft +
                ", locations=" + Arrays.toString(locations) +
                '}';
    }
}
