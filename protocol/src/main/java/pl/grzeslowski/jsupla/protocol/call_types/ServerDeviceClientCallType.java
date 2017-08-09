package pl.grzeslowski.jsupla.protocol.call_types;

public enum ServerDeviceClientCallType {
    SUPLA_SDC_CALL_GETVERSION_RESULT(20),
    SUPLA_SDC_CALL_VERSIONERROR(30),
    SUPLA_SDC_CALL_PING_SERVER_RESULT(50),
    /**
     * @since ver. 2
     */
    SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT(220);


    private final int value;

    ServerDeviceClientCallType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
