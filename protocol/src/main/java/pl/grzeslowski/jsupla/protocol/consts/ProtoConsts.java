package pl.grzeslowski.jsupla.protocol.consts;

public final class ProtoConsts {

    public static final byte[] SUPLA_TAG = new byte[]{83, 85, 80, 76, 65};

    /**
     * Max data packet that server can get is 10kB.
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
