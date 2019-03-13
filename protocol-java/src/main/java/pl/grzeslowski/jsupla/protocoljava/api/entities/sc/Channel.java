package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.Valid;
import javax.validation.constraints.*;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class Channel implements ServerClientEntity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int eol;
    @Positive
    @Min(0) // FIXME remove after random beans are updated
    private final int id;
    private final int locationId;
    private final int function;
    private final boolean online;
    @NotNull
    @Valid
    private final pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue channelValue;
    @NotNull
    @Size(min = 1, max = SUPLA_CHANNEL_CAPTION_MAXSIZE)
    private final String caption;

    public Channel(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int eol,
                   final @Positive @Min(0) int id,
                   final int locationId,
                   final int function,
                   final boolean online,
                   final @NotNull @Valid pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue channelValue,
                   final @NotNull @Size(min = 1, max = SUPLA_CHANNEL_CAPTION_MAXSIZE) String caption) {
        this.eol = byteSize(eol);
        this.id = min(id, 1);
        this.locationId = locationId;
        this.function = function;
        this.online = online;
        this.channelValue = requireNonNull(channelValue);
        this.caption = size(caption, 1, SUPLA_CHANNEL_CAPTION_MAXSIZE);
    }

    public int getEol() {
        return eol;
    }

    public int getId() {
        return id;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getFunction() {
        return function;
    }

    public boolean isOnline() {
        return online;
    }

    public pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue getChannelValue() {
        return channelValue;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Channel)) {
            return false;
        }

        final Channel channel = (Channel) o;

        if (eol != channel.eol) {
            return false;
        }
        if (id != channel.id) {
            return false;
        }
        if (locationId != channel.locationId) {
            return false;
        }
        if (function != channel.function) {
            return false;
        }
        if (online != channel.online) {
            return false;
        }
        if (!channelValue.equals(channel.channelValue)) {
            return false;
        }
        return caption.equals(channel.caption);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "eol=" + eol +
                ", id=" + id +
                ", locationId=" + locationId +
                ", function=" + function +
                ", online=" + online +
                ", channelValue=" + channelValue +
                '}';
    }
}
