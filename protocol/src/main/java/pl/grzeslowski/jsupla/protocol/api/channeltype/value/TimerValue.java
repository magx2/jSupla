package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.Value;

import java.time.Duration;

@Value
public class TimerValue implements ChannelValue {
    Duration remaining;
    byte[] targetValue;
    int senderId;
    String senderName;
}
