package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;


import lombok.RequiredArgsConstructor;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ChannelTypeDecoder {
    public static final ChannelTypeDecoder INSTANCE = new ChannelTypeDecoder();
    private final ColorTypeChannelDecoderImpl colorTypeChannelDecoder;
    private final RelayTypeChannelDecoderImpl relayTypeChannelDecoder;
    private final ThermometerTypeChannelDecoderImpl thermometerTypeChannelDecoder;
    private final ElectricityMeterDecoderImpl electricityMeterDecoder;
    private final ElectricityMeterV2DecoderImpl electricityMeterV2Decoder;

    private ChannelTypeDecoder() {
        this(new ColorTypeChannelDecoderImpl(),
            new RelayTypeChannelDecoderImpl(),
            new ThermometerTypeChannelDecoderImpl(),
            new ElectricityMeterDecoderImpl(),
            new ElectricityMeterV2DecoderImpl());
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
            case SUPLA_CHANNELTYPE_DIMMER:
            case SUPLA_CHANNELTYPE_RGBLEDCONTROLLER:
            case SUPLA_CHANNELTYPE_DIMMERANDRGBLED:
            case SUPLA_CHANNELTYPE_DISTANCESENSOR:
                return colorTypeChannelDecoder.decode(value);
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V1:
                return electricityMeterDecoder.decode(value);
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V2:
                return electricityMeterV2Decoder.decode(value);
            case UNKNOWN:
                return UnknownValue.UNKNOWN_VALUE;
            default:
                return new UnknownValue(value, format("Don't know how to map channel type %s to channel value!",
                    channelType));
        }
    }

    public Class<? extends ChannelValue> findClass(int type) {
        val optional = ChannelType.findByValue(type).map(this::findClass);
        if (!optional.isPresent()) {
            return UnknownValue.class;
        }
        return optional.get();
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
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
            case SUPLA_CHANNELTYPE_DIMMER:
            case SUPLA_CHANNELTYPE_RGBLEDCONTROLLER:
            case SUPLA_CHANNELTYPE_DIMMERANDRGBLED:
            case SUPLA_CHANNELTYPE_DISTANCESENSOR:
                return RgbValue.class;
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V1:
            case EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V2:
            case SUPLA_CHANNELTYPE_ELECTRICITY_METER:
                return ElectricityMeterValue.class;
            case UNKNOWN:
                return UnknownValue.class;
            default:
                return UnknownValue.class;
        }
    }
}
