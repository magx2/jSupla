package pl.grzeslowski.jsupla.protocol.api.channeltype;


import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.UnknownValue;
import pl.grzeslowski.jsupla.protocol.api.traits.DeviceChannelTrait;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ChannelTypeDecoder {
    public static final ChannelTypeDecoder INSTANCE = new ChannelTypeDecoder();
    private final ColorTypeChannelDecoderImpl colorTypeChannelDecoder;
    private final RelayTypeChannelDecoderImpl relayTypeChannelDecoder;
    private final ThermometerTypeChannelDecoderImpl thermometerTypeChannelDecoder;

    public ChannelTypeDecoder() {
        this(new ColorTypeChannelDecoderImpl(),
            new RelayTypeChannelDecoderImpl(),
            new ThermometerTypeChannelDecoderImpl());
    }

    public ChannelValue decode(DeviceChannelTrait deviceChannelTrait) {
        return decode(deviceChannelTrait.getType(), deviceChannelTrait.getValue());
    }

    public ChannelValue decode(int type, byte[] value) {
        return ChannelType.findByValue(type)
            .map(t -> decode(t, value))
            .orElseGet(() ->
                new UnknownValue(
                    new byte[0],
                    format("Don't know how to map device channel type %s to channel value", type)));
    }

    @SuppressWarnings("deprecation")
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
            case UNKNOWN:
                return UnknownValue.UNKNOWN_VALUE;
            default:
                return new UnknownValue(value, format("Don't know how to map channel type %s to channel value!",
                    channelType));
        }
    }
}
