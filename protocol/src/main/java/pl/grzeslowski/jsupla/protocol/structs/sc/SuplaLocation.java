package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

import java.util.Arrays;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public final class SuplaLocation implements ServerClient {
    public final byte eol;
    public final int id;
    /**
     * Including the terminating null byte ('\0').
     *
     * <p>unsigned
     */
    public final long captionSize;
    /**
     * Last variable in struct.
     */
    public final byte[] caption;

    public SuplaLocation(byte eol, int id, long captionSize, byte[] caption) {
        this.eol = eol;
        this.id = id;
        this.captionSize = captionSize;
        if (captionSize > SUPLA_LOCATION_CAPTION_MAXSIZE) {
            throw new IllegalArgumentException(
                    format("captionSize (%s) is bigger than SUPLA_LOCATION_CAPTION_MAXSIZE (%s)!",
                            captionSize, SUPLA_LOCATION_CAPTION_MAXSIZE));
        }
        this.caption = Preconditions.size(caption, 0, captionSize);
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
        return "SuplaLocation{" +
                "eol=" + eol +
                ", id=" + id +
                ", captionSize=" + captionSize +
                ", caption=" + Arrays.toString(caption) +
                '}';
    }
}
