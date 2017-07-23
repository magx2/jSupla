package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public final class TSC_SuplaLocation  implements Proto {
    public final byte eol;
    public final int id;
    /**
     * including the terminating null byte ('\0')
     *
     * unsigned
     */
    public final int captionSize;
    /**
     * Last variable in struct!
     */
    public final byte[] caption;

    public TSC_SuplaLocation(byte eol, int id, int captionSize, byte[] caption) {
        this.eol = eol;
        this.id = id;
        this.captionSize = captionSize;
        this.caption = checkArrayLength(caption, SUPLA_LOCATION_CAPTION_MAXSIZE);
    }

    @Override
    public String toString() {
        return "TSC_SuplaLocation{" +
                "eol=" + eol +
                ", id=" + id +
                ", captionSize=" + captionSize +
                ", caption=" + Arrays.toString(caption) +
                '}';
    }
}
