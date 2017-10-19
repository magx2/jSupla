package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_LOCATION_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;

public final class SuplaLocation implements ServerClient {
    public static final int MIN_SIZE = BYTE_SIZE + INT_SIZE * 2;
    public final byte eol;
    public final int id;
    /**
     * Including the terminating null byte ('\0').
     * <p>
     * <p>unsigned
     */
    public final long captionSize;
    /**
     * Last variable in struct.
     */
    public final byte[] caption;

    public SuplaLocation(byte eol, int id, long captionSize, byte[] caption) {
        this.eol = eol;
        this.id = min(id, 1);
        this.captionSize = max(captionSize, SUPLA_LOCATION_CAPTION_MAXSIZE);
        this.caption = checkArrayLength(caption, (int) captionSize);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_LOCATION_UPDATE;
    }

    @Override
    public int size() {
        return MIN_SIZE + caption.length;
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
