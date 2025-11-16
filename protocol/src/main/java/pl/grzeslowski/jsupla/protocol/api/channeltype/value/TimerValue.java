package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.time.Duration;
import lombok.Value;

/**
 * @param remaining
 * @param targetValue
 * @param senderId
 * @param senderName
 */
public record TimerValue(Duration remaining, byte[] targetValue, int senderId, String senderName) implements ChannelValue {
}
