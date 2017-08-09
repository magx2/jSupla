package pl.grzeslowski.jsupla.protocol.calltypes;

public enum ServerDeviceCallType implements CallType {
    SUPLA_SD_CALL_REGISTER_DEVICE_RESULT(70),
    SUPLA_SD_CALL_CHANNEL_SET_VALUE(110),
    /**
     * @since ver. 5
     */
    SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT(310);

    private final int value;

    ServerDeviceCallType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
