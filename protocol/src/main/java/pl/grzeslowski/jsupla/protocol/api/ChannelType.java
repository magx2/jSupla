package pl.grzeslowski.jsupla.protocol.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Arrays;
import java.util.Optional;

@SuppressWarnings({"SpellCheckingInspection", "DeprecatedIsStillUsed"})
@Getter
@Slf4j
@RequiredArgsConstructor
public enum ChannelType {
    @Deprecated
    SUPLA_CHANNELTYPE_SENSORNO(1000),

    /**
     * use BINARYSENSOR instead
     */
    SUPLA_CHANNELTYPE_BINARYSENSOR(1000),
    @Deprecated
    SUPLA_CHANNELTYPE_SENSORNC(1010),

    /**
     * @since version 5
     */
    SUPLA_CHANNELTYPE_DISTANCESENSOR(1020),

    /**
     * @since version 4
     */
    SUPLA_CHANNELTYPE_CALLBUTTON(1500),
    @Deprecated
    SUPLA_CHANNELTYPE_RELAYHFD4(2000),
    @Deprecated
    SUPLA_CHANNELTYPE_RELAYG5LA1A(2010),
    @Deprecated
    SUPLA_CHANNELTYPE_2XRELAYG5LA1A(2020),
    SUPLA_CHANNELTYPE_RELAY(2900),
    @Deprecated
    SUPLA_CHANNELTYPE_THERMOMETERDS18B20(3000),

    /**
     * @since version 4
     */
    @Deprecated
    SUPLA_CHANNELTYPE_DHT11(3010),

    /**
     * @since version 4
     */
    @Deprecated
    SUPLA_CHANNELTYPE_DHT22(3020),

    /**
     * @since version 5
     */
    @Deprecated
    SUPLA_CHANNELTYPE_DHT21(3022),

    /**
     * @since version 4
     */
    @Deprecated
    SUPLA_CHANNELTYPE_AM2302(3030),

    /**
     * @since version 5
     */
    @Deprecated
    SUPLA_CHANNELTYPE_AM2301(3032),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_THERMOMETER(3034),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_HUMIDITYSENSOR(3036),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_HUMIDITYANDTEMPSENSOR(3038),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_WINDSENSOR(3042),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_PRESSURESENSOR(3044),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_RAINSENSOR(3048),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_WEIGHTSENSOR(3050),

    /**
     * @since version 8
     */
    SUPLA_CHANNELTYPE_WEATHER_STATION(3100),

    /**
     * @since version 4
     */
    SUPLA_CHANNELTYPE_DIMMER(4000),

    /**
     * @since version 4
     */
    SUPLA_CHANNELTYPE_RGBLEDCONTROLLER(4010),

    /**
     * @since version 4
     */
    SUPLA_CHANNELTYPE_DIMMERANDRGBLED(4020),

    /**
     * @since version 10
     */
    SUPLA_CHANNELTYPE_ELECTRICITY_METER(5000),

    /**
     * @since version 10
     */
    SUPLA_CHANNELTYPE_IMPULSE_COUNTER(5010),

    /**
     * @since version 11
     */
    SUPLA_CHANNELTYPE_THERMOSTAT(6000),

    /**
     * @since version 11
     */
    SUPLA_CHANNELTYPE_THERMOSTAT_HEATPOL_HOMEPLUS(6010),

    /**
     * @since version 21
     */
    SUPLA_CHANNELTYPE_HVAC(6100),

    /**
     * @since version 12
     */
    SUPLA_CHANNELTYPE_VALVE_OPENCLOSE(7000),

    /**
     * @since version 12
     */
    SUPLA_CHANNELTYPE_VALVE_PERCENTAGE(7010),

    /**
     * @since version 12
     */
    SUPLA_CHANNELTYPE_BRIDGE(8000),

    /**
     * @since version 23
     */
    SUPLA_CHANNELTYPE_GENERAL_PURPOSE_MEASUREMENT(9000),

    /**
     * @since version 23
     */
    SUPLA_CHANNELTYPE_GENERAL_PURPOSE_METER(9010),

    /**
     * @since version 12
     */
    SUPLA_CHANNELTYPE_ENGINE(10000),

    /**
     * @since version 16
     */
    SUPLA_CHANNELTYPE_ACTIONTRIGGER(11000),

    /**
     * @since version 12
     */
    SUPLA_CHANNELTYPE_DIGIGLASS(12000),

    /**
     * This is when you don't know ChannelType, i.e. when smth was added to cloud/device code without changing it here.
     */
    UNKNOWN(-1);

    private final int value;

    public static Optional<ChannelType> findByValue(int value) {
        val any = Arrays.stream(values())
            .filter(type -> type.value == value)
            .findAny();
        if (!any.isPresent()) {
            log.warn("Unknown channel type value: {}", value);
        }
        return any;
    }
}
