package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * Value for channels whose function is {@code SUPLA_BIT_FUNC_CONTROLLINGTHEROLLERSHUTTER}.
 *
 * <p>The relay payload maps {@link #OPEN} to byte {@code 1} and {@link #CLOSE} to byte {@code 0}.
 */
public enum RollerShutterValue implements ChannelValue {
    OPEN,
    CLOSE
}
