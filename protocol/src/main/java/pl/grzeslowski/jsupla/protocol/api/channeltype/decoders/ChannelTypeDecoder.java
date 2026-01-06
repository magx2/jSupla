package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

@Slf4j
@RequiredArgsConstructor(access = PRIVATE)
public final class ChannelTypeDecoder {
    public static final ChannelTypeDecoder INSTANCE = new ChannelTypeDecoder();
    private final ColorTypeChannelDecoderImpl colorTypeChannelDecoder;
    private final RelayTypeChannelDecoderImpl relayTypeChannelDecoder;
    private final ThermometerTypeChannelDecoderImpl thermometerTypeChannelDecoder;
    private final ThermometerTypeDoubleChannelDecoderImpl thermometerTypeDoubleChannelDecoder;
    private final ElectricityMeterDecoderImpl electricityMeterDecoder;
    private final ElectricityMeterV2DecoderImpl electricityMeterV2Decoder;
    private final HVACValueDecoderImpl hvacValueDecoder;
    private final TimerSecChannelDecoder timerSecChannelDecoder;
    private final TimerMsecChannelDecoder timerMsecChannelDecoder;
    private final PercentageTypeDecoder percentageTypeDecoder;
    private final HumidityTypeChannelDecoderImpl humidityTypeChannelDecoder;

    private ChannelTypeDecoder() {
        this(
                new ColorTypeChannelDecoderImpl(),
                new RelayTypeChannelDecoderImpl(),
                new ThermometerTypeChannelDecoderImpl(),
                new ThermometerTypeDoubleChannelDecoderImpl(),
                new ElectricityMeterDecoderImpl(),
                new ElectricityMeterV2DecoderImpl(),
                HVACValueDecoderImpl.INSTANCE,
                new TimerSecChannelDecoder(),
                new TimerMsecChannelDecoder(),
                new PercentageTypeDecoder(),
            new HumidityTypeChannelDecoderImpl());
    }

    public ChannelValue decode(int type, byte[] value) {
        return ChannelType.findByValue(type)
                .map(t -> decode(t, value))
                .orElseGet(
                        () ->
                                new UnknownValue(
                                        new byte[0],
                                        format(
                                                "Don't know how to map device channel type %s to"
                                                        + " channel value",
                                                type)));
    }

    public ChannelValue decode(final ChannelType channelType, final byte[] value) {
        if (channelType == null) {
            return new UnknownValue(value, "Channel type is null");
        }
        return switch (channelType) {
            //noinspection deprecation
            case SUPLA_CHANNELTYPE_SENSORNO,
                    SUPLA_CHANNELTYPE_SENSORNC,
                    SUPLA_CHANNELTYPE_RELAYHFD4,
                    SUPLA_CHANNELTYPE_RELAYG5LA1A,
                    SUPLA_CHANNELTYPE_2XRELAYG5LA1A,
                    SUPLA_CHANNELTYPE_RELAY ->
                    relayTypeChannelDecoder.decode(value);
            case SUPLA_CHANNELTYPE_HUMIDITYSENSOR -> humidityTypeChannelDecoder.decode(value);
            //noinspection deprecation
            case                     SUPLA_CHANNELTYPE_DHT11,
                    SUPLA_CHANNELTYPE_DHT22,
                    SUPLA_CHANNELTYPE_DHT21,
                    SUPLA_CHANNELTYPE_AM2302,
                    SUPLA_CHANNELTYPE_AM2301,
                    SUPLA_CHANNELTYPE_HUMIDITYANDTEMPSENSOR ->
                    thermometerTypeChannelDecoder.decode(value);
            //noinspection deprecation
            case SUPLA_CHANNELTYPE_THERMOMETER,
                 SUPLA_CHANNELTYPE_THERMOMETERDS18B20-> 
                thermometerTypeDoubleChannelDecoder.decode(value);
            case SUPLA_CHANNELTYPE_DIMMER -> percentageTypeDecoder.decode(value);
            case SUPLA_CHANNELTYPE_RGBLEDCONTROLLER,
                    SUPLA_CHANNELTYPE_DIMMERANDRGBLED,
                    SUPLA_CHANNELTYPE_DISTANCESENSOR ->
                    colorTypeChannelDecoder.decode(value);
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V1 -> electricityMeterDecoder.decode(value);
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V2 ->
                    electricityMeterV2Decoder.decode(value);
            case SUPLA_CHANNELTYPE_HVAC -> hvacValueDecoder.decode(value);
            case EV_TYPE_TIMER_STATE_V1 -> timerMsecChannelDecoder.decode(value);
            case EV_TYPE_TIMER_STATE_V1_SEC -> timerSecChannelDecoder.decode(value);
            case SUPLA_CHANNELTYPE_ACTIONTRIGGER -> {
                log.error(
                        "Can not decode SUPLA_CHANNELTYPE_ACTIONTRIGGER, value={}",
                        Arrays.toString(value));
                yield new UnknownValue(value, "Can not decode SUPLA_CHANNELTYPE_ACTIONTRIGGER");
            }
            default -> {
                val message =
                        format(
                                "Don't know how to map channel type %s to channel value!",
                                channelType);
                if (log.isWarnEnabled()) {
                    log.warn(message + " value={}", Arrays.toString(value));
                }
                yield new UnknownValue(value, message);
            }
        };
    }

    public Class<? extends ChannelValue> findClass(int type) {
        val optional = ChannelType.findByValue(type).map(this::findClass);
        if (optional.isEmpty()) {
            return UnknownValue.class;
        }
        return optional.get();
    }

    public Class<? extends ChannelValue> findClass(final ChannelType channelType) {
        if (channelType == null) {
            return UnknownValue.class;
        }
        return switch (channelType) {
            case SUPLA_CHANNELTYPE_SENSORNO,
                    SUPLA_CHANNELTYPE_SENSORNC,
                    SUPLA_CHANNELTYPE_RELAYHFD4,
                    SUPLA_CHANNELTYPE_RELAYG5LA1A,
                    SUPLA_CHANNELTYPE_2XRELAYG5LA1A,
                    SUPLA_CHANNELTYPE_RELAY ->
                    OnOff.class;
            case SUPLA_CHANNELTYPE_THERMOMETERDS18B20,
                    SUPLA_CHANNELTYPE_DHT11,
                    SUPLA_CHANNELTYPE_DHT22,
                    SUPLA_CHANNELTYPE_DHT21,
                    SUPLA_CHANNELTYPE_AM2302,
                    SUPLA_CHANNELTYPE_AM2301 ->
                    TemperatureAndHumidityValue.class;
            case SUPLA_CHANNELTYPE_THERMOMETER -> TemperatureValue.class;
            case SUPLA_CHANNELTYPE_DIMMER -> PercentValue.class;
            case SUPLA_CHANNELTYPE_RGBLEDCONTROLLER,
                    SUPLA_CHANNELTYPE_DIMMERANDRGBLED,
                    SUPLA_CHANNELTYPE_DISTANCESENSOR ->
                    RgbValue.class;
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V1,
                    EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V2,
                    SUPLA_CHANNELTYPE_ELECTRICITY_METER ->
                    ElectricityMeterValue.class;
            case SUPLA_CHANNELTYPE_HVAC -> HvacValue.class;
            case SUPLA_CHANNELTYPE_ACTIONTRIGGER -> ActionTrigger.class;
            case EV_TYPE_TIMER_STATE_V1, EV_TYPE_TIMER_STATE_V1_SEC -> TimerValue.class;
            default -> {
                log.warn("Don't know how to map channel type {} to channel value!", channelType);
                yield UnknownValue.class;
            }
        };
    }
}
