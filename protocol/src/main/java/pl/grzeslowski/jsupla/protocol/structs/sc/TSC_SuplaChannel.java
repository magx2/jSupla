package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.call_types.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.structs.TSuplaChannelValue;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

public final class TSC_SuplaChannel implements ServerClient {
    public final byte eol;
    public final int id;
    public final int locationId;
    public final int func;
    public final byte online;
    public final TSuplaChannelValue value;
    /**
     * Including the terminating null byte ('\0')
     * <p>
     * unsigned
     */
    public final int captionSize;
    public final byte[] caption;

    public TSC_SuplaChannel(byte eol, int id, int locationId, int func, byte online, TSuplaChannelValue value, int captionSize, byte[] caption) {
        this.eol = eol;
        this.id = id;
        this.locationId = locationId;
        this.func = func;
        this.online = online;
        this.value = value;
        this.captionSize = captionSize;
        this.caption = Preconditions.size(caption, 0, SUPLA_CHANNEL_CAPTION_MAXSIZE);
    }


    @Override
    public ServerClientCallType callType() {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        return BYTE_SIZE * 2 + INT_SIZE * 4 + value.size() + caption.length;
    }

    @Override
    public String toString() {
        return "TSC_SuplaChannel{" +
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