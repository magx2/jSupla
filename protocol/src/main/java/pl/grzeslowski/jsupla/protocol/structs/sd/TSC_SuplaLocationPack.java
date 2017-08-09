package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.consts.CallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXSIZE;

public final class TSC_SuplaLocationPack implements ServerDevice {
    public final int count;
    public final int totalLeft;
    public final TSC_SuplaLocation[] locations;

    public TSC_SuplaLocationPack(int count, int totalLeft, TSC_SuplaLocation[] locations) {
        this.count = count;
        this.totalLeft = totalLeft;
        this.locations = Preconditions.size(locations, 0, SUPLA_LOCATIONPACK_MAXSIZE);
    }

    @Override
    public CallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return INT_SIZE * 2 + locationsSize();
    }

    private int locationsSize() {
        return Arrays.stream(locations)
                .mapToInt(TSC_SuplaLocation::size)
                .sum();
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
