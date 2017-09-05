package pl.grzeslowski.jsupla.protocoljava.api.channeltypes;

public enum ChannelType {
    SUPLA_CHANNELTYPE_SENSORNO(1000),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_SENSORNC(1010),
    /**
     * @since ver. >= 5
     */
    SUPLA_CHANNELTYPE_DISTANCESENSOR(1020),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_CALLBUTTON(1500),
    SUPLA_CHANNELTYPE_RELAYHFD4(2000),
    SUPLA_CHANNELTYPE_RELAYG5LA1A(2010),
    SUPLA_CHANNELTYPE_2XRELAYG5LA1A(2020),
    SUPLA_CHANNELTYPE_RELAY(2900),
    SUPLA_CHANNELTYPE_THERMOMETERDS18B20(3000),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_DHT11(3010),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_DHT22(3020),
    /**
     * @since ver. >= 5
     */
    SUPLA_CHANNELTYPE_DHT21(3022),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_AM2302(3030),
    /**
     * @since ver. >= 5
     */
    SUPLA_CHANNELTYPE_AM2301(3032),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_DIMMER(4000),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_RGBLEDCONTROLLER(4010),
    /**
     * @since ver. >= 4
     */
    SUPLA_CHANNELTYPE_DIMMERANDRGBLED(4020);

    private final int value;

    ChannelType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
