package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.time.Duration;
import lombok.NonNull;

/**
 * @param remaining
 * @param targetValue
 * @param senderId
 * @param senderName
 */
public record TimerValue(
        @NonNull Duration remaining, byte[] targetValue, int senderId, @NonNull String senderName)
        implements ChannelValue {}
