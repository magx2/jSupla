package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_PACK_MAXCOUNT;

public class ChannelValuePack implements ServerClientEntity {
    @PositiveOrZero
    private final int totalLeft;
    @Size(max = SUPLA_CHANNELVALUE_PACK_MAXCOUNT)
    private final List<ChannelValue> items;

    public ChannelValuePack(@PositiveOrZero int totalLeft,
                            @Size(max = SUPLA_CHANNELVALUE_PACK_MAXCOUNT) List<ChannelValue> items) {
        this.totalLeft = totalLeft;
        this.items = items;
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public List<ChannelValue> getItems() {
        return items;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelValuePack)) return false;
        ChannelValuePack that = (ChannelValuePack) o;
        return totalLeft == that.totalLeft &&
                Objects.equals(items, that.items);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(totalLeft, items);
    }

    @Override
    public String toString() {
        return "ChannelValuePack{" +
                "totalLeft=" + totalLeft +
                ", items=" + items +
                '}';
    }
}
