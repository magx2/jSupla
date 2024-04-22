package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders;

import java.util.Arrays;
import java.util.Optional;

public enum ChannelType {
    SUPLA_CHANNELTYPE_SENSORNO(1000),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_SENSORNC(1010),
    /**
     * @since ver. &gt;= 5
     */
    SUPLA_CHANNELTYPE_DISTANCESENSOR(1020),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_CALLBUTTON(1500),
    SUPLA_CHANNELTYPE_RELAYHFD4(2000),
    SUPLA_CHANNELTYPE_RELAYG5LA1A(2010),
    SUPLA_CHANNELTYPE_2XRELAYG5LA1A(2020),
    SUPLA_CHANNELTYPE_RELAY(2900),
    SUPLA_CHANNELTYPE_THERMOMETERDS18B20(3000),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_DHT11(3010),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_DHT22(3020),
    /**
     * @since ver. &gt;= 5
     */
    SUPLA_CHANNELTYPE_DHT21(3022),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_AM2302(3030),
    /**
     * @since ver. &gt;= 5
     */
    SUPLA_CHANNELTYPE_AM2301(3032),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_DIMMER(4000),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_RGBLEDCONTROLLER(4010),
    /**
     * @since ver. &gt;= 4
     */
    SUPLA_CHANNELTYPE_DIMMERANDRGBLED(4020),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_THERMOMETER(3034),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_HUMIDITYSENSOR(3036),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_HUMIDITYANDTEMPSENSOR(3038),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_WINDSENSOR(3042),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_PRESSURESENSOR(3044),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_RAINSENSOR(3048),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_WEIGHTSENSOR(3050),
    /**
     * @since ver. 8
     */
    SUPLA_CHANNELTYPE_WEATHER_STATION(3100),

    /**
     * This is when you don't know ChannelType, i.e. when smth was added to cloud/device code without changing it here.
     */
    UNKNOWN(-1);

    private final int value;

    ChannelType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Optional<ChannelType> findByValue(int value) {
        return Arrays.stream(ChannelType.values())
            .filter(type -> type.value == value)
            .findAny();
    }
}
