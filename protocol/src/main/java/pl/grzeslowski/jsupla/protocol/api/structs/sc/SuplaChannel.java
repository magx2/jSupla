package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

@EqualsAndHashCode
@ToString
public final class SuplaChannel implements ProtoWithSize {
    public static final int MIN_SIZE = BYTE_SIZE * 2 + INT_SIZE * 4 + SuplaChannelValue.SIZE;

    public final byte eol;
    public final int id;
    public final int locationId;
    public final int func;
    public final byte online;
    public final SuplaChannelValue value;
    /**
     * Including the terminating null byte ('\0').
     *
     * <p>unsigned
     */
    public final long captionSize;
    public final byte[] caption;

    public SuplaChannel(byte eol,
                        int id,
                        int locationId,
                        int func,
                        byte online,
                        SuplaChannelValue value,
                        long captionSize,
                        byte[] caption) {
        this.eol = eol;
        this.id = min(id, 1);
        this.locationId = locationId;
        this.func = func;
        this.online = online;
        this.value = value;
        this.captionSize = max(captionSize, SUPLA_CHANNEL_CAPTION_MAXSIZE);
        this.caption = Preconditions.checkArrayLength(caption, (int) captionSize);
    }

    @Override
    public int size() {
        return MIN_SIZE + caption.length;
    }
}
