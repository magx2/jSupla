package pl.grzeslowski.jsupla.protocoljava.api.entities.cs;

import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;

import javax.validation.constraints.NotNull;

import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.B;

public class ChannelNewValueB extends ChannelNewValue {
    private final int channelId;

    public ChannelNewValueB(final int channelId,
                            final @NotNull ChannelValue value) {
        super(0, value);
        this.channelId = channelId;
    }

    @Override
    public int getChannelId() {
        return channelId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelNewValueB)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final ChannelNewValueB that = (ChannelNewValueB) o;

        return channelId == that.channelId;
    }

    @Override
    public int hashCode() {
        return channelId;
    }

    @Override
    public Version version() {
        return B;
    }

    @Override
    public String toString() {
        return "ChannelNewValueB{" +
                       "channelId=" + channelId +
                       "} " + super.toString();
    }
}
