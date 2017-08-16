package pl.grzeslowski.jsupla.protocol.api.calltypes;

public enum DeviceServerCallType implements CallType {
    SUPLA_DS_CALL_REGISTER_DEVICE(60),
    /**
     * @since ver. 2
     */
    SUPLA_DS_CALL_REGISTER_DEVICE_B(65),
    /**
     * @since ver. 6
     */
    SUPLA_DS_CALL_REGISTER_DEVICE_C(67),
    SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED(100),
    SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT(120),
    /**
     * @since ver. 5
     */
    SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL(300);

    private final int value;

    DeviceServerCallType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
