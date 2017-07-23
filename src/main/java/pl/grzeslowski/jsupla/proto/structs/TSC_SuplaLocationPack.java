package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXSIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public  final class TSC_SuplaLocationPack  implements Proto {
    public final int count;
    public final int totalLeft;
    public final TSC_SuplaLocation[] locations;

    public TSC_SuplaLocationPack(int count, int totalLeft, TSC_SuplaLocation[] locations) {
        this.count = count;
        this.totalLeft = totalLeft;
        this.locations = checkArrayLength(locations, SUPLA_LOCATIONPACK_MAXSIZE);
    }

    @Override
    public int size() {
        assert locations[0] != null;
        return  INT_SIZE *2+ locations[0].size() * SUPLA_LOCATIONPACK_MAXSIZE;
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
