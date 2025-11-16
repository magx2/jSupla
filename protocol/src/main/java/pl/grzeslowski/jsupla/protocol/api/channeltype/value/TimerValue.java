package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.time.Duration;
import lombok.Value;

@Value
public class TimerValue implements ChannelValue {
    Duration remaining;
    byte[] targetValue;
    int senderId;
    String senderName;
}
