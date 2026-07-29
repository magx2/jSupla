package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * Value for channels whose function is {@code SUPLA_BIT_FUNC_POWERSWITCH}.
 *
 * <p>The relay payload maps {@link #ON} to byte {@code 1} and {@link #OFF} to byte {@code 0}.
 */
public enum PowerSwitchValue implements ChannelValue {
    ON,
    OFF
}
