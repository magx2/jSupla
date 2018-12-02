package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;

import java.util.Arrays;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedIntSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_UPDATE_B;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since ver. 8
 */
public final class SuplaChannelB implements ServerClient {
    public static final int MIN_SIZE = BYTE_SIZE * 3 + INT_SIZE * 6 + pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.SIZE;

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
     * unsigned byte
     */
    public final short protocolVersion;
    public final byte online;
    public final SuplaChannelValue value;
    /**
     * Including the terminating null byte ('\0').
     * <p>
     * <p>unsigned
     */
    public final long captionSize;
    public final byte[] caption;

    public SuplaChannelB(final byte eol,
                         final int id,
                         final int locationId,
                         final int func,
                         final int altIcon,
                         final long flags,
                         final short protocolVersion,
                         final byte online,
                         final SuplaChannelValue value,
                         final long captionSize,
                         final byte[] caption) {
        this.eol = eol;
        this.id = id;
        this.locationId = locationId;
        this.func = func;
        this.altIcon = altIcon;
        this.flags = unsignedIntSize(flags);
        this.protocolVersion = unsignedByteSize(protocolVersion);
        this.online = online;
        this.value = value;
        this.captionSize = unsignedIntSize(captionSize);
        this.caption = caption;
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNEL_UPDATE_B;
    }

    @Override
    public int size() {
        return MIN_SIZE + caption.length;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SuplaChannelB that = (SuplaChannelB) o;
        return eol == that.eol &&
                       id == that.id &&
                       locationId == that.locationId &&
                       func == that.func &&
                       altIcon == that.altIcon &&
                       flags == that.flags &&
                       protocolVersion == that.protocolVersion &&
                       online == that.online &&
                       captionSize == that.captionSize &&
                       Objects.equals(value, that.value) &&
                       Arrays.equals(caption, that.caption);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(eol, id, locationId, func, altIcon, flags, protocolVersion, online, value,
                captionSize);
        result = 31 * result + Arrays.hashCode(caption);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaChannelB{" +
                       "eol=" + eol +
                       ", id=" + id +
                       ", locationId=" + locationId +
                       ", func=" + func +
                       ", altIcon=" + altIcon +
                       ", flags=" + flags +
                       ", protocolVersion=" + protocolVersion +
                       ", online=" + online +
                       ", value=" + value +
                       ", captionSize=" + captionSize +
                       ", caption=" + Arrays.toString(caption) +
                       '}';
    }
}
