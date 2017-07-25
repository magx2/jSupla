package pl.grzeslowski.jsupla.consts;

public final class ProtoConsts {

    public static final byte[] SUPLA_TAG = new byte[]{83, 85, 80, 76, 65};

    /**
     * Max data packet that server can get is 10kB
     */
    public static final int SUPLA_MAX_DATA_SIZE = 10240;

    public static final int SUPLA_TAG_SIZE = 5;
    public static final int SUPLA_RC_MAX_DEV_COUNT = 50;
    public static final int SUPLA_SOFTVER_MAXSIZE = 21;

    public static final int SUPLA_GUID_SIZE = 16;
    public static final int SUPLA_GUID_HEXSIZE = 33;
    public static final int SUPLA_LOCATION_PWDHEX_MAXSIZE = 65;
    public static final int SUPLA_LOCATION_PWD_MAXSIZE = 33;
    public static final int SUPLA_ACCESSID_PWDHEX_MAXSIZE = 65;
    public static final int SUPLA_ACCESSID_PWD_MAXSIZE = 33;
    public static final int SUPLA_LOCATION_CAPTION_MAXSIZE = 401;
    public static final int SUPLA_LOCATIONPACK_MAXSIZE = 20;
    public static final int SUPLA_CHANNEL_CAPTION_MAXSIZE = 401;
    public static final int SUPLA_CHANNELPACK_MAXSIZE = 20;
    public static final int SUPLA_URL_HOST_MAXSIZE = 101;
    public static final int SUPLA_URL_PATH_MAXSIZE = 101;
    public static final int SUPLA_SERVER_NAME_MAXSIZE = 65;

    public static final int SUPLA_DCS_CALL_GETVERSION = 10;
    public static final int SUPLA_SDC_CALL_GETVERSION_RESULT = 20;
    public static final int SUPLA_SDC_CALL_VERSIONERROR = 30;
    public static final int SUPLA_DCS_CALL_PING_SERVER = 40;
    public static final int SUPLA_SDC_CALL_PING_SERVER_RESULT = 50;
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE = 60;
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE_B = 65; // ver. >= 2
    public static final int SUPLA_DS_CALL_REGISTER_DEVICE_C = 67; // ver. >= 6
    public static final int SUPLA_SD_CALL_REGISTER_DEVICE_RESULT = 70;
    public static final int SUPLA_CS_CALL_REGISTER_CLIENT = 80;
    public static final int SUPLA_CS_CALL_REGISTER_CLIENT_B = 85; // ver. >= 6
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
    public static final int SUPLA_CS_CALL_CHANNEL_SET_VALUE_B = 205; // ver. >= 3
    public static final int SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT = 210; // ver. >= 2
    public static final int SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT = 220; // ver. >= 2
    public static final int SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL = 300; // ver. >= 5
    public static final int SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT = 310; // ver. >= 5

    public static final int SUPLA_RESULT_DATA_TOO_LARGE = -4;
    public static final int SUPLA_RESULT_BUFFER_OVERFLOW = -3;
    public static final int SUPLA_RESULT_DATA_ERROR = -2;
    public static final int SUPLA_RESULT_VERSION_ERROR = -1;
    public static final int SUPLA_RESULT_FALSE = 0;
    public static final int SUPLA_RESULT_TRUE = 1;

    public static final int SUPLA_RESULTCODE_NONE = 0;
    public static final int SUPLA_RESULTCODE_UNSUPORTED = 1;
    public static final int SUPLA_RESULTCODE_FALSE = 2;
    public static final int SUPLA_RESULTCODE_TRUE = 3;
    public static final int SUPLA_RESULTCODE_TEMPORARILY_UNAVAILABLE = 4;
    public static final int SUPLA_RESULTCODE_BAD_CREDENTIALS = 5;
    public static final int SUPLA_RESULTCODE_LOCATION_CONFLICT = 6;
    public static final int SUPLA_RESULTCODE_CHANNEL_CONFLICT = 7;
    public static final int SUPLA_RESULTCODE_DEVICE_DISABLED = 8;
    public static final int SUPLA_RESULTCODE_ACCESSID_DISABLED = 9;
    public static final int SUPLA_RESULTCODE_LOCATION_DISABLED = 10;
    public static final int SUPLA_RESULTCODE_CLIENT_DISABLED = 11;
    public static final int SUPLA_RESULTCODE_CLIENT_LIMITEXCEEDED = 12;
    public static final int SUPLA_RESULTCODE_DEVICE_LIMITEXCEEDED = 13;
    public static final int SUPLA_RESULTCODE_GUID_ERROR = 14;
    public static final int SUPLA_RESULTCODE_HOSTNOTFOUND = 15; // ver. >= 5
    public static final int SUPLA_RESULTCODE_CANTCONNECTTOHOST = 16; // ver. >= 5

    public static final int SUPLA_DEVICE_NAME_MAXSIZE = 201;
    public static final int SUPLA_DEVICE_NAMEHEX_MAXSIZE = 401;
    public static final int SUPLA_CLIENT_NAME_MAXSIZE = 201;
    public static final int SUPLA_CLIENT_NAMEHEX_MAXSIZE = 401;
    public static final int SUPLA_SENDER_NAME_MAXSIZE = 201;

    public static final int SUPLA_CHANNELVALUE_SIZE = 8;

    public static final int SUPLA_CHANNELMAXCOUNT = 128;

    private ProtoConsts() {
    }
}
