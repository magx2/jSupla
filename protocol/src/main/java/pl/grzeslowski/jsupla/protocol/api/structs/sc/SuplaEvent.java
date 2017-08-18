package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
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
        this.senderNameSize = senderNameSize;
        this.senderName = checkArrayLength(senderName, SUPLA_SENDER_NAME_MAXSIZE);
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
