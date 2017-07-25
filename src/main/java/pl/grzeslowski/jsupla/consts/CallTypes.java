package pl.grzeslowski.jsupla.consts;

public final class CallTypes {
    public static final int SUPLA_DCS_CALL_GETVERSION = 10;
    public static final int SUPLA_SDC_CALL_GETVERSION_RESULT = 20;
    public static final int SUPLA_SDC_CALL_VERSIONERROR = 30;
    public static final int SUPLA_DCS_CALL_PING_SERVER = 40;
    public static final int SUPLA_SDC_CALL_PING_SERVER_RESULT = 50;
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE = 60;
    /**
     * @since 2
     */
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE_B = 65;
    /**
     * @since 6
     */
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE_C = 67;
    public static final int SUPLA_SD_CALL_REGISTER_DEVICE_RESULT = 70;
    public static final int SUPLA_CS_CALL_REGISTER_CLIENT = 80;
    /**
     * @since 6
     */
    public static final int SUPLA_CS_CALL_REGISTER_CLIENT_B = 85;
    public static final int SUPLA_SC_CALL_REGISTER_CLIENT_RESULT = 90;
    public static final int SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED = 100;
    public static final int SUPLA_SD_CALL_CHANNEL_SET_VALUE = 110;
    public static final int SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT = 120;
    public static final int SUPLA_SC_CALL_LOCATION_UPDATE = 130;
    public static final int SUPLA_SC_CALL_LOCATIONPACK_UPDATE = 140;
    public static final int SUPLA_SC_CALL_CHANNEL_UPDATE = 150;
    public static final int SUPLA_SC_CALL_CHANNELPACK_UPDATE = 160;
    public static final int SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE = 170;
    public static final int SUPLA_CS_CALL_GET_NEXT = 180;
    public static final int SUPLA_SC_CALL_EVENT = 190;
    public static final int SUPLA_CS_CALL_CHANNEL_SET_VALUE = 200;
    /**
     * @since 3
     */
    public static final int SUPLA_CS_CALL_CHANNEL_SET_VALUE_B = 205;
    /**
     * @since 2
     */
    public static final int SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT = 210;
    /**
     * @since 2
     */
    public static final int SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT = 220;
    /**
     * @since 5
     */
    public static final int SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL = 300;
    /**
     * @since 5
     */
    public static final int SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT = 310;

    private CallTypes() {
    }
}
