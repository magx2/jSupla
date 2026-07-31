package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

/**
 * Value for channels whose function is {@code SUPLA_BIT_FUNC_CONTROLLINGTHEGATE}.
 *
 * <p>The relay payload maps {@link #OPEN} to byte {@code 1} and {@link #CLOSE} to byte {@code 0}.
 */
public enum GateValue implements AbstractOnOffValue {
    OPEN,
    CLOSE;

    @Override
    public OnOffValue toCommonBase() {
        return switch (this) {
            case OPEN -> OnOffValue.ON;
            case CLOSE -> OnOffValue.OFF;
        };
    }
}
