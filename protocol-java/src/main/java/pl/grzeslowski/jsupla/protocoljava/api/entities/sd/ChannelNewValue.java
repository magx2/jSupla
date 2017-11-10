package pl.grzeslowski.jsupla.protocoljava.api.entities.sd;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.id;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedIntSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_INT_MAX;

public class ChannelNewValue implements ServerDeviceEntity {
    @Positive
    @Min(1) // FIXME random beans
    private final int senderId;
    @PositiveOrZero
    @Min(0) // FIXME random beans
    @Max(UNSIGNED_BYTE_MAX)
    private final int channelNumber;
    @PositiveOrZero
    @Min(0) // FIXME random beans
    @Max(UNSIGNED_INT_MAX)
    private final long durationMs;
    @NotNull
    @Valid
    private final ChannelValue value;

    public ChannelNewValue(@Positive final int senderId,
                           @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int channelNumber,
                           @PositiveOrZero @Max(UNSIGNED_INT_MAX) final long durationMs,
                           final @NotNull @Valid ChannelValue value) {
        this.senderId = id(senderId);
        this.channelNumber = unsignedByteSize(channelNumber);
        this.durationMs = unsignedIntSize(durationMs);
        this.value = requireNonNull(value);
    }

    public int getSenderId() {
        return senderId;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public ChannelValue getValue() {
        return value;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelNewValue)) {
            return false;
        }

        final ChannelNewValue that = (ChannelNewValue) o;

        if (senderId != that.senderId) {
            return false;
        }
        if (channelNumber != that.channelNumber) {
            return false;
        }
        if (durationMs != that.durationMs) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public final int hashCode() {
        return senderId;
    }

    @Override
    public String toString() {
        return "ChannelNewValue{" +
                       "senderId=" + senderId +
                       ", channelNumber=" + channelNumber +
                       ", durationMs=" + durationMs +
                       ", value=" + value +
                       '}';
    }
}
