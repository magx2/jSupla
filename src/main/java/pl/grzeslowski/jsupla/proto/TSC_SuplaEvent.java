package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public final class TSC_SuplaEvent implements Proto  {
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
        this.senderName = senderName;
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
