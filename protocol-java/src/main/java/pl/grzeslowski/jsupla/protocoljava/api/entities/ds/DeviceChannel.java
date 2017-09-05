package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class DeviceChannel implements DeviceServerEntity {
    @Min(0)
    @Max(UNSIGNED_BYTE_MAX)
    private final int number;
    private final int type;
    @NotNull private final ChannelValue value;

    public DeviceChannel(@Min(0) @Max(UNSIGNED_BYTE_MAX) final int number,
                         final int type,
                         final @NotNull ChannelValue value) {
        this.number = unsignedByteSize(number);
        this.type = type;
        this.value = requireNonNull(value);
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public ChannelValue getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceChannel)) {
            return false;
        }

        final DeviceChannel that = (DeviceChannel) o;

        if (number != that.number) {
            return false;
        }
        if (type != that.type) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "DeviceChannel{" +
                       "number=" + number +
                       ", type=" + type +
                       ", value=" + value +
                       '}';
    }
}
