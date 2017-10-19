package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ColorTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.RelayTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ThermometerTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.UnknownValue;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ChannelTypeDecoderImpl implements ChannelTypeDecoder {
    private final ColorTypeChannelDecoder colorTypeChannelDecoder;
    private final RelayTypeChannelDecoder relayTypeChannelDecoder;
    private final ThermometerTypeChannelDecoder thermometerTypeChannelDecoder;

    public ChannelTypeDecoderImpl(final ColorTypeChannelDecoder colorTypeChannelDecoder,
                                  final RelayTypeChannelDecoder relayTypeChannelDecoder,
                                  final ThermometerTypeChannelDecoder thermometerTypeChannelDecoder) {
        this.colorTypeChannelDecoder = requireNonNull(colorTypeChannelDecoder);
        this.relayTypeChannelDecoder = requireNonNull(relayTypeChannelDecoder);
        this.thermometerTypeChannelDecoder = requireNonNull(thermometerTypeChannelDecoder);
    }

    @Override
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
                return thermometerTypeChannelDecoder.decode(value);
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
