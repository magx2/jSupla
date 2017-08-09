package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXSIZE;

public final class SuplaLocationPack implements ServerClient {
    public final int count;
    public final int totalLeft;
    public final SuplaLocation[] locations;

    public SuplaLocationPack(int count, int totalLeft, SuplaLocation[] locations) {
        this.count = count;
        this.totalLeft = totalLeft;
        this.locations = Preconditions.size(locations, 0, SUPLA_LOCATIONPACK_MAXSIZE);
    }

    @Override
    public ServerClientCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return INT_SIZE * 2 + locationsSize();
    }

    private int locationsSize() {
        return Arrays.stream(locations)
                .mapToInt(SuplaLocation::size)
                .sum();
    }

    @Override
    public String toString() {
        return "SuplaLocationPack{" +
                "count=" + count +
                ", totalLeft=" + totalLeft +
                ", locations=" + Arrays.toString(locations) +
                '}';
    }
}
