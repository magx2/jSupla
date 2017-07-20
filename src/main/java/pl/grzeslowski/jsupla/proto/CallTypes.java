package pl.grzeslowski.jsupla.proto;

import static java.lang.Integer.MIN_VALUE;

public class CallTypes {
    public static final int SUPLA_DCS_CALL_GETVERSION = 10 + MIN_VALUE;
    public static final int SUPLA_SDC_CALL_GETVERSION_RESULT = 20 + MIN_VALUE;
    public static final int SUPLA_SDC_CALL_VERSIONERROR = 30 + MIN_VALUE;
    public static final int SUPLA_DCS_CALL_PING_SERVER = 40 + MIN_VALUE;
    public static final int SUPLA_SDC_CALL_PING_SERVER_RESULT = 50 + MIN_VALUE;
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE = 60 + MIN_VALUE;
    /**
     * @since 2
     */
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE_B = 65 + MIN_VALUE;
    /**
     * @since 6
     */
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE_C = 67 + MIN_VALUE;
    public static final int SUPLA_SD_CALL_REGISTER_DEVICE_RESULT = 70 + MIN_VALUE;
    public static final int SUPLA_CS_CALL_REGISTER_CLIENT = 80 + MIN_VALUE;
    /**
     * @since 6
     */
    public static final int SUPLA_CS_CALL_REGISTER_CLIENT_B = 85 + MIN_VALUE;
    public static final int SUPLA_SC_CALL_REGISTER_CLIENT_RESULT = 90 + MIN_VALUE;
    public static final int SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED = 100 + MIN_VALUE;
    public static final int SUPLA_SD_CALL_CHANNEL_SET_VALUE = 110 + MIN_VALUE;
    public static final int SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT = 120 + MIN_VALUE;
    public static final int SUPLA_SC_CALL_LOCATION_UPDATE = 130 + MIN_VALUE;
    public static final int SUPLA_SC_CALL_LOCATIONPACK_UPDATE = 140 + MIN_VALUE;
    public static final int SUPLA_SC_CALL_CHANNEL_UPDATE = 150 + MIN_VALUE;
    public static final int SUPLA_SC_CALL_CHANNELPACK_UPDATE = 160 + MIN_VALUE;
    public static final int SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE = 170 + MIN_VALUE;
    public static final int SUPLA_CS_CALL_GET_NEXT = 180 + MIN_VALUE;
    public static final int SUPLA_SC_CALL_EVENT = 190 + MIN_VALUE;
    public static final int SUPLA_CS_CALL_CHANNEL_SET_VALUE = 200 + MIN_VALUE;
    /**
     * @since 3
     */
    public static final int SUPLA_CS_CALL_CHANNEL_SET_VALUE_B = 205 + MIN_VALUE;
    /**
     * @since 2
     */
    public static final int SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT = 210 + MIN_VALUE;
    /**
     * @since 2
     */
    public static final int SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT = 220 + MIN_VALUE;
    /**
     * @since 5
     */
    public static final int SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL = 300 + MIN_VALUE;
    /**
     * @since 5
     */
    public static final int SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT = 310 + MIN_VALUE;
}
