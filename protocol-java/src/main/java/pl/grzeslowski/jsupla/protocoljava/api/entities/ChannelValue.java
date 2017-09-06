package pl.grzeslowski.jsupla.protocoljava.api.entities;

import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class ChannelValue implements Entity {
    @NotNull private final pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue value;
    private final pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue subValue;

    @SuppressWarnings("WeakerAccess")
    public ChannelValue(final @NotNull pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue value,
                        final pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue subValue) {
        this.value = requireNonNull(value);
        this.subValue = subValue;
    }

    public ChannelValue(final @NotNull pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue value) {
        this(value, null);
    }

    public pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue getValue() {
        return value;
    }

    public Optional<pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue> getSubValue() {
        return Optional.ofNullable(subValue);
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

        if (!value.equals(that.value)) {
            return false;
        }
        return subValue != null ? subValue.equals(that.subValue) : that.subValue == null;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "ChannelValue{" +
                       "value=" + value +
                       ", subValue=" + subValue +
                       '}';
    }
}
