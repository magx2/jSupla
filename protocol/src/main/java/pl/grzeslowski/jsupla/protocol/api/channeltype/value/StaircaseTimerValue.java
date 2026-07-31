package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * Value for channels whose function is {@code SUPLA_BIT_FUNC_STAIRCASETIMER}.
 *
 * <p>The relay payload maps {@link #ON} to byte {@code 1} and {@link #OFF} to byte {@code 0}.
 */
public enum StaircaseTimerValue implements AbstractOnOffValue {
    ON,
    OFF;

    @Override
    public OnOffValue toCommonBase() {
        return switch (this) {
            case ON -> OnOffValue.ON;
            case OFF -> OnOffValue.OFF;
        };
    }
}
