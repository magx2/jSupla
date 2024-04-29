package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.Preconditions.unsignedIntSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

/**
 * @since ver. 8
 */

@EqualsAndHashCode
@ToString
public final class SuplaChannelB implements ProtoWithSize {
    public static final int MIN_SIZE = BYTE_SIZE * 3 + INT_SIZE * 6
        + pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.SIZE;

    public final byte eol;
    public final int id;
    public final int locationId;
    public final int func;
    public final int altIcon;
    /**
     * unsigned int.
     */
    public final long flags;
    /**
     * unsigned byte.
     */
    public final short protocolVersion;
    public final byte online;
    public final SuplaChannelValue value;
    /**
     * Including the terminating null byte ('\0').
     *
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
    public int size() {
        return MIN_SIZE + caption.length;
    }

}
