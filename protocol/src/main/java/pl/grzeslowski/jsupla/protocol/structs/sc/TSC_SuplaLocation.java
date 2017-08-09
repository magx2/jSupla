package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public final class TSC_SuplaLocation implements ServerClient {
    public final byte eol;
    public final int id;
    /**
     * Including the terminating null byte ('\0').
     * <p>
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
        this.caption = Preconditions.size(caption, 0, SUPLA_LOCATION_CAPTION_MAXSIZE);
    }

    @Override
    public ServerClientCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE * 2 + caption.length;
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
