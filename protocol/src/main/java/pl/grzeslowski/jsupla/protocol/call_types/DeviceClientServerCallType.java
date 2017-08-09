package pl.grzeslowski.jsupla.protocol.call_types;

public enum DeviceClientServerCallType {
    SUPLA_DCS_CALL_GETVERSION(10),
    SUPLA_DCS_CALL_PING_SERVER(40),
    /**
     * @since ver. 2
     */
    SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT(210);

    private final int value;

    DeviceClientServerCallType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
