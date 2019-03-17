package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.positive;
import static pl.grzeslowski.jsupla.Preconditions.size;

public class ChannelGroupRelation implements ServerClientEntity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int eol;
    @Positive
    private final int channelGroupId;
    @Positive
    private final int channelId;

    public ChannelGroupRelation(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) int eol,
                                @Positive int channelGroupId,
                                @Positive int channelId) {
        this.eol = size(eol, Byte.MIN_VALUE, Byte.MAX_VALUE);
        this.channelGroupId = positive(channelGroupId);
        this.channelId = positive(channelId);
    }

    public int getEol() {
        return eol;
    }

    public int getChannelGroupId() {
        return channelGroupId;
    }

    public int getChannelId() {
        return channelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChannelGroupRelation that = (ChannelGroupRelation) o;
        return eol == that.eol &&
                channelGroupId == that.channelGroupId &&
                channelId == that.channelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eol, channelGroupId, channelId);
    }

    @Override
    public String toString() {
        return "ChannelGroupRelation{" +
                "eol=" + eol +
                ", channelGroupId=" + channelGroupId +
                ", channelId=" + channelId +
                '}';
    }
}
