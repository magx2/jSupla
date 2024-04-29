package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.Value;

@Value
public class UnknownValue implements ChannelValue {
    public static final UnknownValue UNKNOWN_VALUE = new UnknownValue(new byte[0], "UNKNOWN_VALUE");

    byte[] bytes;
    String message;
}
