package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * @param bytes
 * @param message
 */
public record UnknownValue(byte[] bytes, String message) implements ChannelValue {
    public static final UnknownValue UNKNOWN_VALUE = new UnknownValue(new byte[0], "UNKNOWN_VALUE");
}
