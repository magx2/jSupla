package pl.grzeslowski.jsupla.consts;

import java.util.Optional;

import static java.util.Arrays.stream;

public enum CallType {
    SUPLA_DCS_CALL_GETVERSION(10),
    SUPLA_SDC_CALL_GETVERSION_RESULT(20),
    SUPLA_SDC_CALL_VERSIONERROR(30),
    SUPLA_DCS_CALL_PING_SERVER(40),
    SUPLA_SDC_CALL_PING_SERVER_RESULT(50),
    SUPLA_DS_CALL_REGISTER_DEVICE(60),
    /**
     * @since 2
     */
    SUPLA_DS_CALL_REGISTER_DEVICE_B(65),
    /**
     * @since 6
     */
    SUPLA_DS_CALL_REGISTER_DEVICE_C(67),
    SUPLA_SD_CALL_REGISTER_DEVICE_RESULT(70),
    SUPLA_CS_CALL_REGISTER_CLIENT(80),
    /**
     * @since 6
     */
    SUPLA_CS_CALL_REGISTER_CLIENT_B(85),
    SUPLA_SC_CALL_REGISTER_CLIENT_RESULT(90),
    SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED(100),
    SUPLA_SD_CALL_CHANNEL_SET_VALUE(110),
    SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT(120),
    SUPLA_SC_CALL_LOCATION_UPDATE(130),
    SUPLA_SC_CALL_LOCATIONPACK_UPDATE(140),
    SUPLA_SC_CALL_CHANNEL_UPDATE(150),
    SUPLA_SC_CALL_CHANNELPACK_UPDATE(160),
    SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE(170),
    SUPLA_CS_CALL_GET_NEXT(180),
    SUPLA_SC_CALL_EVENT(190),
    SUPLA_CS_CALL_CHANNEL_SET_VALUE(200),
    /**
     * @since 3
     */
    SUPLA_CS_CALL_CHANNEL_SET_VALUE_B(205),
    /**
     * @since 2
     */
    SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT(210),
    /**
     * @since 2
     */
    SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT(220),
    /**
     * @since 5
     */
    SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL(300),
    /**
     * @since 5
     */
    SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT(310);

    private final int value;

    CallType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Optional<CallType> findByValue(long value) {
        return stream(CallType.values()).filter(e -> e.value == value).findFirst();
    }
}
