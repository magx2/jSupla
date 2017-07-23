package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public final  class TSC_SuplaChannel implements Proto {
    public final byte eol;
    public final int id;
    public final int locationId;
    public final int func;
    public final byte online;
    public final TSuplaChannelValue value;
    /**
     * Including the terminating null byte ('\0')
     *
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
        this.caption = checkArrayLength(caption, SUPLA_CHANNEL_CAPTION_MAXSIZE);
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
