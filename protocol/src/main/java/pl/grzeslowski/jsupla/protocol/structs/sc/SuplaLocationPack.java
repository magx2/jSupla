package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

import java.util.Arrays;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXSIZE;

public final class SuplaLocationPack implements ServerClient {
    public final int count;
    public final int totalLeft;
    public final SuplaLocation[] locations;

    public SuplaLocationPack(int count, int totalLeft, SuplaLocation[] locations) {
        this.count = count;
        if (count > SUPLA_LOCATIONPACK_MAXSIZE) {
            throw new IllegalArgumentException(format("count (%s) is bigger than SUPLA_LOCATIONPACK_MAXSIZE (%S)",
                    count, SUPLA_LOCATIONPACK_MAXSIZE));
        }
        this.totalLeft = totalLeft;
        this.locations = Preconditions.size(locations, 0, count);
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
