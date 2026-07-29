package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * Value for channels whose function is {@code SUPLA_BIT_FUNC_VERTICAL_BLIND}.
 *
 * <p>The relay payload maps {@link #OPEN} to byte {@code 1} and {@link #CLOSE} to byte {@code 0}.
 */
public enum VerticalBlindValue implements ChannelValue {
    OPEN,
    CLOSE
}
