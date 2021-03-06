package pl.grzeslowski.jsupla.protocol.api;

public enum ResultCode {
    SUPLA_RESULTCODE_NONE(0),
    SUPLA_RESULTCODE_UNSUPORTED(1),
    SUPLA_RESULTCODE_FALSE(2),
    SUPLA_RESULTCODE_TRUE(3),
    SUPLA_RESULTCODE_TEMPORARILY_UNAVAILABLE(4),
    SUPLA_RESULTCODE_BAD_CREDENTIALS(5),
    SUPLA_RESULTCODE_LOCATION_CONFLICT(6),
    SUPLA_RESULTCODE_CHANNEL_CONFLICT(7),
    SUPLA_RESULTCODE_DEVICE_DISABLED(8),
    SUPLA_RESULTCODE_ACCESSID_DISABLED(9),
    SUPLA_RESULTCODE_LOCATION_DISABLED(10),
    SUPLA_RESULTCODE_CLIENT_DISABLED(11),
    SUPLA_RESULTCODE_CLIENT_LIMITEXCEEDED(12),
    SUPLA_RESULTCODE_DEVICE_LIMITEXCEEDED(13),
    SUPLA_RESULTCODE_GUID_ERROR(14),
    /**
     * @since ver. 5
     */
    SUPLA_RESULTCODE_HOSTNOTFOUND(15),
    /**
     * @since ver. 5
     */
    SUPLA_RESULTCODE_CANTCONNECTTOHOST(16),
    /**
     * @since ver. 7
     */
    SUPLA_RESULTCODE_REGISTRATION_DISABLED(17),
    /**
     * @since ver. 7
     */
    SUPLA_RESULTCODE_ACCESSID_NOT_ASSIGNED(18),
    /**
     * @since ver. 7
     */
    SUPLA_RESULTCODE_AUTHKEY_ERROR(19),
    /**
     * @since ver. 7
     */
    SUPLA_RESULTCODE_NO_LOCATION_AVAILABLE(20),
    /**
     * @since ver. 7
     */
    SUPLA_RESULTCODE_USER_CONFLICT(21);

    private final int value;

    ResultCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
