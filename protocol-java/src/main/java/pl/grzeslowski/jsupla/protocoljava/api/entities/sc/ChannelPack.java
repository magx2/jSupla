package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELPACK_MAXSIZE;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class ChannelPack implements ServerClientEntity {
    @PositiveOrZero private final int totalLeft;
    @NotNull
    @Size(min = 1, max = SUPLA_CHANNELPACK_MAXSIZE)
    private final List<? extends Channel> channels;

    public ChannelPack(@PositiveOrZero final int totalLeft,
                       @NotNull @Size(min = 1, max = SUPLA_CHANNELPACK_MAXSIZE) List<? extends Channel> channels) {
        this.totalLeft = min(totalLeft, 0);
        this.channels = unmodifiableList(new ArrayList<>(requireNonNull(channels)));
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public List<? extends Channel> getChannels() {
        return channels;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelPack)) {
            return false;
        }

        final ChannelPack that = (ChannelPack) o;

        if (totalLeft != that.totalLeft) {
            return false;
        }
        return channels.equals(that.channels);
    }

    @Override
    public final int hashCode() {
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
