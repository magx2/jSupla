package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

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
        this.caption = caption;
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
