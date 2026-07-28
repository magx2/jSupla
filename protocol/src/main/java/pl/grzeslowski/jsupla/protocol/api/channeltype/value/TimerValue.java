package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.time.Duration;
import java.util.Arrays;
import lombok.NonNull;

/**
 * @param remaining
 * @param targetValue
 * @param senderId
 * @param senderName
 */
public record TimerValue(
        @NonNull Duration remaining, byte[] targetValue, int senderId, @NonNull String senderName)
        implements ChannelValue {
    @Override
    public String toString() {
        return "TimerValue[remaining="
                + remaining
                + ", targetValue="
                + Arrays.toString(targetValue)
                + ", senderId="
                + senderId
                + ", senderName="
                + senderName
                + "]";
    }
}
