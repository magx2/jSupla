package pl.grzeslowski.jsupla.protocol.api.calltypes;

public enum ServerDeviceClientCallType implements CallType {
    SUPLA_SDC_CALL_GETVERSION_RESULT(20),
    SUPLA_SDC_CALL_VERSIONERROR(30),
    SUPLA_SDC_CALL_PING_SERVER_RESULT(50),
    /**
     * @since ver. 2
     */
    SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT(220),
    /**
     * @since ver. 7
     */
    SUPLA_SDC_CALL_GET_REGISTRATION_ENABLED_RESULT(330);


    private final int value;

    ServerDeviceClientCallType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
