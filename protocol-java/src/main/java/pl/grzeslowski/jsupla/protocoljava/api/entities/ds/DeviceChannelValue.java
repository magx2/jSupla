package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.protocol.api.channelvalues.ChannelValue;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class DeviceChannelValue implements DeviceServerEntity {
    @Min(0)
    @Max(UNSIGNED_BYTE_MAX)
    private final int channelNumber;
    @Valid
    @NotNull
    private final ChannelValue value;

    public DeviceChannelValue(@Min(0) @Max(UNSIGNED_BYTE_MAX) final int channelNumber,
                              final @Valid @NotNull ChannelValue value) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.value = requireNonNull(value);
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public ChannelValue getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceChannelValue)) {
            return false;
        }

        final DeviceChannelValue that = (DeviceChannelValue) o;

        if (channelNumber != that.channelNumber) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return channelNumber;
    }

    @Override
    public String toString() {
        return "DeviceChannelValue{" +
                       "channelNumber=" + channelNumber +
                       ", value=" + value +
                       '}';
    }
}
