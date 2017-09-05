package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.byteSize;

public class ChannelValue implements ServerClientEntity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int eol;
    private final int id;
    private final boolean online;
    @NotNull
    @Valid
    private final pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue value;

    public ChannelValue(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int eol,
                        final int id,
                        final boolean online,
                        final pl.grzeslowski.jsupla.protocoljava.api.entities.@NotNull @Valid ChannelValue value) {
        this.eol = byteSize(eol);
        this.id = id;
        this.online = online;
        this.value = requireNonNull(value);
    }

    public int getEol() {
        return eol;
    }

    public int getId() {
        return id;
    }

    public boolean isOnline() {
        return online;
    }

    public pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelValue)) {
            return false;
        }

        final ChannelValue that = (ChannelValue) o;

        if (eol != that.eol) {
            return false;
        }
        if (id != that.id) {
            return false;
        }
        if (online != that.online) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ChannelValue{" +
                       "eol=" + eol +
                       ", id=" + id +
                       ", online=" + online +
                       ", value=" + value +
                       '}';
    }
}
