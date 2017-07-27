package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public final class TSC_SuplaEvent implements ServerDevice {
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
    public CallType callType() {
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
