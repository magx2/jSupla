package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.equalsTo;
import static pl.grzeslowski.jsupla.Preconditions.id;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELGROUP_PACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since ver. 9
 */
public final class SuplaChannelGroup implements ServerClient {
    public static final int MIN_SIZE = BYTE_SIZE + INT_SIZE * 6;

    public final byte eol;
    public final int id;
    public final int locationId;
    public final int func;
    public final int altIcon;
    /**
     * unsigned int
     */
    public final long flags;
    /**
     * Including the terminating null byte ('\0').
     * <p>
     * <p>unsigned
     */
    public final long captionSize;
    public final byte[] caption;

    public SuplaChannelGroup(final byte eol,
                             final int id,
                             final int locationId,
                             final int func,
                             final int altIcon,
                             final long flags,
                             final long captionSize,
                             final byte[] caption) {
        this.eol = eol;
        this.id = id(id);
        this.locationId = id(locationId);
        this.func = func;
        this.altIcon = altIcon;
        this.flags = flags;
        this.captionSize = captionSize;
        this.caption = caption;
        equalsTo(captionSize, caption.length);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNELGROUP_PACK_UPDATE;
    }

    @Override
    public int size() {
        return MIN_SIZE + caption.length * BYTE_SIZE;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SuplaChannelGroup that = (SuplaChannelGroup) o;
        return eol == that.eol &&
                       id == that.id &&
                       locationId == that.locationId &&
                       func == that.func &&
                       altIcon == that.altIcon &&
                       flags == that.flags &&
                       captionSize == that.captionSize &&
                       Arrays.equals(caption, that.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SuplaChannelGroup{" +
                       "eol=" + eol +
                       ", id=" + id +
                       ", locationId=" + locationId +
                       ", func=" + func +
                       ", altIcon=" + altIcon +
                       ", flags=" + flags +
                       ", captionSize=" + captionSize +
                       ", caption=" + Arrays.toString(caption) +
                       '}';
    }
}
