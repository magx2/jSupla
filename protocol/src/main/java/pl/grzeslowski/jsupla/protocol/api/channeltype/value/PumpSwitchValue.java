package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * Value for channels whose function is {@code SUPLA_BIT_FUNC_PUMPSWITCH}.
 *
 * <p>The relay payload maps {@link #ON} to byte {@code 1} and {@link #OFF} to byte {@code 0}.
 */
public enum PumpSwitchValue implements ChannelValue {
    ON,
    OFF
}
