package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.*;

import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public class Location implements ServerClientEntity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int eol;
    @Positive
    @Min(1) // FIXME until new random beans
    private final int id;
    @NotNull
    @Size(min = 1, max = SUPLA_LOCATION_CAPTION_MAXSIZE)
    private final String caption;

    public Location(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int eol,
                    final @Positive int id,
                    final @NotNull @Size(min = 1, max = SUPLA_LOCATION_CAPTION_MAXSIZE) String caption) {
        this.eol = byteSize(eol);
        this.id = min(id, 1);
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
    public final boolean equals(final Object o) {
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
    public final int hashCode() {
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
