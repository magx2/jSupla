package pl.grzeslowski.jsupla.protocoljava.api.entities.sd;

import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedIntSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_INT_MAX;

public class ChannelNewValue implements ServerDeviceEntity {
    private final int senderId;
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int channelNumber;
    @PositiveOrZero
    @Max(UNSIGNED_INT_MAX)
    private final long durationMs;
    @NotNull
    @Valid
    private final ChannelValue value;

    public ChannelNewValue(final int senderId,
                           @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int channelNumber,
                           @PositiveOrZero @Max(UNSIGNED_INT_MAX) final long durationMs,
                           final @NotNull @Valid ChannelValue value) {
        this.senderId = senderId;
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
    public boolean equals(final Object o) {
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
    public int hashCode() {
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
