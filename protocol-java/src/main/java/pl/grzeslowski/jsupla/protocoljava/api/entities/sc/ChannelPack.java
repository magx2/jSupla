package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.sizeMin;

public class ChannelPack implements ServerClientEntity {
    @PositiveOrZero private final int totalLeft;
    @NotNull
    @Size(min = 1)
    private final List<Channel> channels;

    public ChannelPack(@PositiveOrZero final int totalLeft,
                       final @NotNull @Size(min = 1) List<Channel> channels) {
        this.totalLeft = sizeMin(totalLeft, 0);
        this.channels = unmodifiableList(new ArrayList<>(requireNonNull(channels)));
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelPack)) return false;

        final ChannelPack that = (ChannelPack) o;

        if (totalLeft != that.totalLeft) return false;
        return channels.equals(that.channels);
    }

    @Override
    public int hashCode() {
        int result = totalLeft;
        result = 31 * result + channels.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ChannelPack{" +
                       "totalLeft=" + totalLeft +
                       ", channels=" + channels +
                       '}';
    }
}
