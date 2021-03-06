package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

public class ChannelB extends Channel {
    private final int altIcon;
    @PositiveOrZero
    private final long flags;
    @Max(UNSIGNED_BYTE_MAX)
    @Min(0)
    private final int protocolVersion;

    public ChannelB(
        @Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) int eol,
        @Positive @Min(0) int id,
        int locationId,
        int function,
        boolean online,
        @NotNull @Valid ChannelValue channelValue,
        @NotNull @Size(min = 1, max = SUPLA_CHANNEL_CAPTION_MAXSIZE) String caption,
        int altIcon,
        @PositiveOrZero long flags,
        @Min(0) @Max(UNSIGNED_BYTE_MAX) int protocolVersion) {
        super(eol, id, locationId, function, online, channelValue, caption);
        this.altIcon = altIcon;
        this.flags = positiveOrZero(flags);
        this.protocolVersion = size(protocolVersion, 0, UNSIGNED_BYTE_MAX);
    }

    public int getAltIcon() {
        return altIcon;
    }

    public long getFlags() {
        return flags;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelB)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final ChannelB channelB = (ChannelB) o;
        if (!channelB.canEqual(this)) {
            return false;
        }
        return altIcon == channelB.altIcon &&
                   flags == channelB.flags &&
                   protocolVersion == channelB.protocolVersion;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ChannelB;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), altIcon, flags, protocolVersion);
    }

    @Override
    public String toString() {
        return "ChannelB{" +
                "altIcon=" + altIcon +
                ", flags=" + flags +
                ", protocolVersion=" + protocolVersion +
                "} " + super.toString();
    }
}
