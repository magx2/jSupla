package pl.grzeslowski.jsupla.protocol.api.calltypes;

public enum ClientServerCallType implements CallType {
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
    SUPLA_CS_CALL_CHANNEL_SET_VALUE_B(205),
    /**
     * @since ver. 7
     */
    SUPLA_CS_CALL_GET_OAUTH_PARAMETERS(340),
    /**
     * @since ver. 9
     */
    SUPLA_CS_CALL_SET_VALUE(410);

    private final int value;

    ClientServerCallType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
