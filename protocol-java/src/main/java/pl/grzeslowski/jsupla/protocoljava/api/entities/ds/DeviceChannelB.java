package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.B;

public class DeviceChannelB extends DeviceChannel {
    private final int function;
    private final int defaultValue;

    public DeviceChannelB(@Min(0) @Max(UNSIGNED_BYTE_MAX) final int number,
                          final int type,
                          final @NotNull ChannelValue value,
                          final int function,
                          final int defaultValue) {
        super(number, type, value);
        this.function = function;
        this.defaultValue = defaultValue;
    }

    @Override
    public Version version() {
        return B;
    }

    public int getFunction() {
        return function;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceChannelB)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final DeviceChannelB that = (DeviceChannelB) o;

        if (function != that.function) {
            return false;
        }
        return defaultValue == that.defaultValue;
    }

    @Override
    public String toString() {
        return "DeviceChannelB{" +
                       "function=" + function +
                       ", defaultValue=" + defaultValue +
                       "} " + super.toString();
    }
}
