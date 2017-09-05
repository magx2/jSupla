package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;
import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public class Location implements ServerClientEntity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int eol;
    private final int id;
    @NotNull
    @Size(min = 1, max = SUPLA_LOCATION_CAPTION_MAXSIZE)
    private final String caption;

    public Location(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int eol,
                    final int id,
                    final @NotNull @Size(min = 1, max = SUPLA_LOCATION_CAPTION_MAXSIZE) String caption) {
        this.eol = byteSize(eol);
        this.id = id;
        this.caption = size(caption, 1, SUPLA_LOCATION_CAPTION_MAXSIZE);
    }

    public int getEol() {
        return eol;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }

        final Location location = (Location) o;

        if (eol != location.eol) {
            return false;
        }
        if (id != location.id) {
            return false;
        }
        return caption.equals(location.caption);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Location{" +
                       "eol=" + eol +
                       ", id=" + id +
                       ", caption='" + caption + '\'' +
                       '}';
    }
}
