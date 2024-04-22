package pl.grzeslowski.jsupla.protocoljava.api.entities.cs;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.byteSize;

public class ChannelNewValue implements ClientServerEntity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int channelId;
    @NotNull
    private final ChannelValue value;

    @SuppressWarnings("WeakerAccess")
    public ChannelNewValue(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int channelId,
                           final @NotNull ChannelValue value) {
        this.channelId = byteSize(channelId);
        this.value = requireNonNull(value);
    }

    public int getChannelId() {
        return channelId;
    }

    public ChannelValue getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelNewValue)) {
            return false;
        }

        final ChannelNewValue that = (ChannelNewValue) o;

        if (getChannelId() != that.getChannelId()) {
            return false;
        }
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return channelId;
    }

    @Override
    public String toString() {
        return "ChannelNewValue{" +
            "channelId=" + channelId +
            ", value=" + value +
            '}';
    }
}
