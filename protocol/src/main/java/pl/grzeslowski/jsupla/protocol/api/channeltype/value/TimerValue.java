package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.NonNull;

import java.time.Duration;

/**
 * @param remaining
 * @param targetValue
 * @param senderId
 * @param senderName
 */
public record TimerValue(@NonNull Duration remaining, byte[] targetValue, int senderId, @NonNull String senderName)
        implements ChannelValue {}
