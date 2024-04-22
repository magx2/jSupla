package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.Preconditions.unsignedIntSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_INT_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SENDER_NAME_MAXSIZE;

public class Event implements ServerClientEntity {
    private final int event;
    private final int channelId;
    @Positive
    @Max(UNSIGNED_INT_MAX)
    private final long durationMs;
    private final int senderId;
    @Size(min = 1, max = SUPLA_SENDER_NAME_MAXSIZE)
    private final String senderName;

    public Event(final int event,
                 final int channelId,
                 @Positive @Max(UNSIGNED_INT_MAX) final long durationMs,
                 final int senderId,
                 final @Size(min = 1, max = SUPLA_SENDER_NAME_MAXSIZE) String senderName) {
        this.event = event;
        this.channelId = channelId;
        this.durationMs = unsignedIntSize(durationMs);
        this.senderId = senderId;
        this.senderName = size(senderName, 1, SUPLA_SENDER_NAME_MAXSIZE);
    }

    public int getEvent() {
        return event;
    }

    public int getChannelId() {
        return channelId;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }

        final Event event1 = (Event) o;

        if (event != event1.event) {
            return false;
        }
        if (channelId != event1.channelId) {
            return false;
        }
        if (durationMs != event1.durationMs) {
            return false;
        }
        if (senderId != event1.senderId) {
            return false;
        }
        return senderName.equals(event1.senderName);
    }

    @Override
    public final int hashCode() {
        int result = event;
        result = 31 * result + channelId;
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
            "event=" + event +
            ", channelId=" + channelId +
            ", durationMs=" + durationMs +
            ", senderId=" + senderId +
            ", senderName='" + senderName + '\'' +
            '}';
    }
}
