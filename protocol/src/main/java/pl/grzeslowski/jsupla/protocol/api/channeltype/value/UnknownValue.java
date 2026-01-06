package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.NonNull;

/**
 * @param bytes
 * @param message
 */
public record UnknownValue(byte[] bytes,@NonNull String message) implements ChannelValue {
    public static final UnknownValue UNKNOWN_VALUE = new UnknownValue(new byte[0], "UNKNOWN_VALUE");
}
