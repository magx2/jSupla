package pl.grzeslowski.jsupla.protocol.call_types;

public enum ClientServerCallType {
    SUPLA_CS_CALL_REGISTER_CLIENT(80),
    /**
     * @since ver. 6
     */
    SUPLA_CS_CALL_REGISTER_CLIENT_B(85),
    SUPLA_CS_CALL_GET_NEXT(180),
    SUPLA_CS_CALL_CHANNEL_SET_VALUE(200),
    /**
     * @since ver. 3
     */
    SUPLA_CS_CALL_CHANNEL_SET_VALUE_B(205);

    private final int value;

    ClientServerCallType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
