package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_EVENT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public final class SuplaEvent implements ServerClient {
    public static final int SIZE = INT_SIZE * 5 + SUPLA_SENDER_NAME_MAXSIZE;
    public final int event;
    public final int channelId;
    /**
     * unsigned.
     */
    public final long durationMs;
    public final int senderId;
    /**
     * Including the terminating null byte ('\0').
     */
    public final int senderNameSize;
    /**
     * UTF-8.
     */
    public final byte[] senderName;

    public SuplaEvent(int event, int channelId, long durationMs, int senderId, int senderNameSize, byte[] senderName) {
        this.event = event;
        this.channelId = channelId;
        this.durationMs = durationMs;
        this.senderId = senderId;
        this.senderNameSize = max(senderNameSize, SUPLA_SENDER_NAME_MAXSIZE);
        this.senderName = checkArrayLength(senderName, senderNameSize);
    }

    @Override
    public ServerClientCallType callType() {
        return SUPLA_SC_CALL_EVENT;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaEvent)) {
            return false;
        }

        final SuplaEvent that = (SuplaEvent) o;

        if (event != that.event) {
            return false;
        }
        if (channelId != that.channelId) {
            return false;
        }
        if (durationMs != that.durationMs) {
            return false;
        }
        if (senderId != that.senderId) {
            return false;
        }
        if (senderNameSize != that.senderNameSize) {
            return false;
        }
        return Arrays.equals(senderName, that.senderName);
    }

    @Override
    public final int hashCode() {
        int result = event;
        result = 31 * result + channelId;
        result = 31 * result + (int) (durationMs ^ (durationMs >>> 32));
        result = 31 * result + senderId;
        result = 31 * result + senderNameSize;
        result = 31 * result + Arrays.hashCode(senderName);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaEvent{" +
                "event=" + event +
                ", channelId=" + channelId +
                ", durationMs=" + durationMs +
                ", senderId=" + senderId +
                ", senderNameSize=" + senderNameSize +
                ", senderName=" + Arrays.toString(senderName) +
                '}';
    }
}
