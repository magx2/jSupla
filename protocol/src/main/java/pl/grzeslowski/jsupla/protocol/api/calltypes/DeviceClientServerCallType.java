package pl.grzeslowski.jsupla.protocol.api.calltypes;

public enum DeviceClientServerCallType implements CallType {
    SUPLA_DCS_CALL_GETVERSION(10),
    SUPLA_DCS_CALL_PING_SERVER(40),
    /**
     * @since ver. 2
     */
    SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT(210),
    /**
     * @since ver. 7
     */
    SUPLA_DCS_CALL_GET_REGISTRATION_ENABLED(320);

    private final int value;

    DeviceClientServerCallType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
