package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.Preconditions.sizeMin;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXSIZE;

public class LocationPack implements ServerClientEntity {
    @PositiveOrZero private final int totalLeft;
    @Size(min = 1, max = SUPLA_LOCATIONPACK_MAXSIZE) private final List<Location> locations;

    public LocationPack(@PositiveOrZero final int totalLeft,
                        final @Size(min = 1, max = SUPLA_LOCATIONPACK_MAXSIZE) List<Location> locations) {
        this.totalLeft = sizeMin(totalLeft, 0);
        this.locations = unmodifiableList(
                new ArrayList<>(
                                       requireNonNull(
                                               size(locations, 1, SUPLA_LOCATIONPACK_MAXSIZE))));
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public List<Location> getLocations() {
        return locations;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocationPack)) {
            return false;
        }

        final LocationPack that = (LocationPack) o;

        if (totalLeft != that.totalLeft) {
            return false;
        }
        return locations.equals(that.locations);
    }

    @Override
    public int hashCode() {
        int result = totalLeft;
        result = 31 * result + locations.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LocationPack{" +
                       "totalLeft=" + totalLeft +
                       ", locations=" + locations +
                       '}';
    }
}
