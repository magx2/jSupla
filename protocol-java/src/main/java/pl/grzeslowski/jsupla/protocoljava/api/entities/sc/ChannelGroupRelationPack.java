package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;

public class ChannelGroupRelationPack implements ServerClientEntity {
    @PositiveOrZero
    private final int totalLeft;
    private final List<ChannelGroupRelation> items;

    public ChannelGroupRelationPack(@PositiveOrZero int totalLeft, List<ChannelGroupRelation> items) {
        this.totalLeft = positiveOrZero(totalLeft);
        this.items = items;
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public List<ChannelGroupRelation> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelGroupRelationPack)) return false;
        ChannelGroupRelationPack that = (ChannelGroupRelationPack) o;
        return totalLeft == that.totalLeft &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalLeft, items);
    }

    @Override
    public String toString() {
        return "ChannelGroupRelationPack{" +
                "totalLeft=" + totalLeft +
                ", items=" + items +
                '}';
    }
}
