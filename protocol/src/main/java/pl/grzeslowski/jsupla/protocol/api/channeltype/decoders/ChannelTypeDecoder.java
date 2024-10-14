package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

import java.util.Arrays;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

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

    private ChannelTypeDecoder() {
        this(new ColorTypeChannelDecoderImpl(),
            new RelayTypeChannelDecoderImpl(),
            new ThermometerTypeChannelDecoderImpl(),
            new ThermometerTypeDoubleChannelDecoderImpl(),
            new ElectricityMeterDecoderImpl(),
            new ElectricityMeterV2DecoderImpl(),
            HVACValueDecoderImpl.INSTANCE);
    }

    public ChannelValue decode(int type, byte[] value) {
        return ChannelType.findByValue(type)
            .map(t -> decode(t, value))
            .orElseGet(() ->
                new UnknownValue(
                    new byte[0],
                    format("Don't know how to map device channel type %s to channel value", type)));
    }

    public ChannelValue decode(final ChannelType channelType, final byte[] value) {
        switch (channelType) {
            case SUPLA_CHANNELTYPE_SENSORNO:
            case SUPLA_CHANNELTYPE_SENSORNC:
            case SUPLA_CHANNELTYPE_RELAYHFD4:
            case SUPLA_CHANNELTYPE_RELAYG5LA1A:
            case SUPLA_CHANNELTYPE_2XRELAYG5LA1A:
            case SUPLA_CHANNELTYPE_RELAY:
                return relayTypeChannelDecoder.decode(value);
            case SUPLA_CHANNELTYPE_THERMOMETERDS18B20:
            case SUPLA_CHANNELTYPE_DHT11:
            case SUPLA_CHANNELTYPE_DHT22:
            case SUPLA_CHANNELTYPE_DHT21:
            case SUPLA_CHANNELTYPE_AM2302:
            case SUPLA_CHANNELTYPE_AM2301:
                return thermometerTypeChannelDecoder.decode(value);
            case SUPLA_CHANNELTYPE_THERMOMETER:
                return thermometerTypeDoubleChannelDecoder.decode(value);
            case SUPLA_CHANNELTYPE_DIMMER:
            case SUPLA_CHANNELTYPE_RGBLEDCONTROLLER:
            case SUPLA_CHANNELTYPE_DIMMERANDRGBLED:
            case SUPLA_CHANNELTYPE_DISTANCESENSOR:
                return colorTypeChannelDecoder.decode(value);
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V1:
                return electricityMeterDecoder.decode(value);
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V2:
                return electricityMeterV2Decoder.decode(value);
            case SUPLA_CHANNELTYPE_HVAC:
                return hvacValueDecoder.decode(value);
            //ignore those 2 because I do not know what to do with them
            case EV_TYPE_TIMER_STATE_V1:
            case EV_TYPE_TIMER_STATE_V1_SEC:
            case UNKNOWN:
                return UnknownValue.UNKNOWN_VALUE;
            default:
                val message = format("Don't know how to map channel type %s to channel value!",
                    channelType);
                if (log.isDebugEnabled()) {
                    log.debug(message + " value={}", Arrays.toString(value));
                }
                return new UnknownValue(value, message);
        }
    }

    public Class<? extends ChannelValue> findClass(int type) {
        val optional = ChannelType.findByValue(type).map(this::findClass);
        if (!optional.isPresent()) {
            return UnknownValue.class;
        }
        return optional.get();
    }

    public Class<? extends ChannelValue> findClass(final ChannelType channelType) {
        switch (channelType) {
            case SUPLA_CHANNELTYPE_SENSORNO:
            case SUPLA_CHANNELTYPE_SENSORNC:
            case SUPLA_CHANNELTYPE_RELAYHFD4:
            case SUPLA_CHANNELTYPE_RELAYG5LA1A:
            case SUPLA_CHANNELTYPE_2XRELAYG5LA1A:
            case SUPLA_CHANNELTYPE_RELAY:
                return OnOff.class;
            case SUPLA_CHANNELTYPE_THERMOMETERDS18B20:
            case SUPLA_CHANNELTYPE_DHT11:
            case SUPLA_CHANNELTYPE_DHT22:
            case SUPLA_CHANNELTYPE_DHT21:
            case SUPLA_CHANNELTYPE_AM2302:
            case SUPLA_CHANNELTYPE_AM2301:
                return TemperatureAndHumidityValue.class;
            case SUPLA_CHANNELTYPE_THERMOMETER:
                return TemperatureValue.class;
            case SUPLA_CHANNELTYPE_DIMMER:
            case SUPLA_CHANNELTYPE_RGBLEDCONTROLLER:
            case SUPLA_CHANNELTYPE_DIMMERANDRGBLED:
            case SUPLA_CHANNELTYPE_DISTANCESENSOR:
                return RgbValue.class;
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V1:
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V2:
            case SUPLA_CHANNELTYPE_ELECTRICITY_METER:
                return ElectricityMeterValue.class;
            case SUPLA_CHANNELTYPE_HVAC:
                return HvacValue.class;
            case UNKNOWN:
                //ignore those 2 because I do not know what to do with them
            case EV_TYPE_TIMER_STATE_V1:
            case EV_TYPE_TIMER_STATE_V1_SEC:
                return UnknownValue.class;
            default:
                log.warn("Don't know how to map channel type {} to channel value!", channelType);
                return UnknownValue.class;
        }
    }
}
