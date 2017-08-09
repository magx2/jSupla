package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public final class TSC_SuplaEvent implements ServerClient {
    public final int event;
    public final int channelId;
    /**
     * unsigned
     */
    public final int durationMs;
    public final int senderId;
    /**
     * Including the terminating null byte ('\0')
     */
    public final int senderNameSize;
    /**
     * UTF-8
     */
    public final byte[] senderName;

    public TSC_SuplaEvent(int event, int channelId, int durationMs, int senderId, int senderNameSize, byte[] senderName) {
        this.event = event;
        this.channelId = channelId;
        this.durationMs = durationMs;
        this.senderId = senderId;
        this.senderNameSize = senderNameSize;
        this.senderName = checkArrayLength(senderName, SUPLA_SENDER_NAME_MAXSIZE);
    }

    @Override
    public ServerClientCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return INT_SIZE *5 + SUPLA_SENDER_NAME_MAXSIZE;
    }

    @Override
    public String toString() {
        return "TSC_SuplaEvent{" +
                "event=" + event +
                ", channelId=" + channelId +
                ", durationMs=" + durationMs +
                ", senderId=" + senderId +
                ", senderNameSize=" + senderNameSize +
                ", senderName=" + Arrays.toString(senderName) +
                '}';
    }
}
