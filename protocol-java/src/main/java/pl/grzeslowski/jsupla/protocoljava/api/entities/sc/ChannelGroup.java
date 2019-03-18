package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;
import static pl.grzeslowski.jsupla.Preconditions.positive;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;

public class ChannelGroup implements ServerClientEntity {
    @Max(Byte.MAX_VALUE)
    @Min(Byte.MIN_VALUE)
    private final int eol;
    @Positive
    private final int id;
    @Positive
    private final int locationId;
    private final int function;
    private final int altIcon;
    @PositiveOrZero
    private final long flags;
    private final String caption;

    public ChannelGroup(@Max(Byte.MAX_VALUE) @Min(Byte.MIN_VALUE) int eol,
                        @Positive int id,
                        @Positive int locationId,
                        int function, int altIcon,
                        @PositiveOrZero long flags,
                        String caption) {
        this.eol = byteSize(eol);
        this.id = positive(id);
        this.locationId = positive(locationId);
        this.function = function;
        this.altIcon = altIcon;
        this.flags = positiveOrZero(flags);
        this.caption = caption;
    }

    public int getEol() {
        return eol;
    }

    public int getId() {
        return id;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getFunction() {
        return function;
    }

    public int getAltIcon() {
        return altIcon;
    }

    public long getFlags() {
        return flags;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelGroup)) {
            return false;
        }
        ChannelGroup that = (ChannelGroup) o;
        return eol == that.eol &&
                id == that.id &&
                locationId == that.locationId &&
                function == that.function &&
                altIcon == that.altIcon &&
                flags == that.flags &&
                Objects.equals(caption, that.caption);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(eol, id, locationId, function, altIcon, flags, caption);
    }

    @Override
    public String toString() {
        return "ChannelGroup{" +
                "eol=" + eol +
                ", id=" + id +
                ", locationId=" + locationId +
                ", function=" + function +
                ", altIcon=" + altIcon +
                ", flags=" + flags +
                ", caption='" + caption + '\'' +
                '}';
    }
}
