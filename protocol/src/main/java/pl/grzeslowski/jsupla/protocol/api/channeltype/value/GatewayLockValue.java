package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * Value for channels whose function is {@code SUPLA_BIT_FUNC_CONTROLLINGTHEGATEWAYLOCK}.
 *
 * <p>The relay payload maps {@link #UNLOCKED} to byte {@code 1} and {@link #LOCKED} to byte {@code 0}.
 */
public enum GatewayLockValue implements ChannelValue {
    UNLOCKED,
    LOCKED
}
