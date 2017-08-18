package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

public final class SuplaChannel implements ServerClient {
    public static final int MIN_SIZE = BYTE_SIZE * 2 + INT_SIZE * 4 + SuplaChannelValue.SIZE;
    public final byte eol;
    public final int id;
    public final int locationId;
    public final int func;
    public final byte online;
    public final SuplaChannelValue value;
    /**
     * Including the terminating null byte ('\0').
     * <p>
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
        this.id = id;
        this.locationId = locationId;
        this.func = func;
        this.online = online;
        this.value = value;
        this.captionSize = Preconditions.max(captionSize, SUPLA_CHANNEL_CAPTION_MAXSIZE);
        this.caption = Preconditions.size(caption, 0, captionSize);
    }


    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_CHANNEL_UPDATE;
    }

    @Override
    public int size() {
        return MIN_SIZE + caption.length;
    }

    @Override
    public String toString() {
        return "SuplaChannel{" +
                       "eol=" + eol +
                       ", id=" + id +
                       ", locationId=" + locationId +
                       ", func=" + func +
                       ", online=" + online +
                       ", value=" + value +
                       ", captionSize=" + captionSize +
                       ", caption=" + Arrays.toString(caption) +
                       '}';
    }
}
