package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ColorTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.RelayTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ThermometerTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.UnknownValue;

import java.util.Arrays;

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
    public ChannelValue decode(final SuplaDeviceChannel channel) {
        return decode(findChannelType(channel.type), channel.value);
    }

    @Override
    public ChannelValue decode(final SuplaDeviceChannelB channel) {
        return decode(findChannelType(channel.type), channel.value);
    }

    @Override
    public ChannelValue decode(final SuplaChannelNewValue channel) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final SuplaChannelNewValueB channel) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final SuplaDeviceChannelValue supla) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue supla) {
        return null; // TODO
    }

    @Override
    public ChannelValue decode(final byte[] value) {
        return null; // TODO
    }

    private ChannelValue decode(ChannelType channelType, byte[] value) {
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
            default:
                return new UnknownValue(value, format("Don't know how to map channel type %s to channel value!",
                        channelType));
        }
    }

    @Override
    public ChannelValue decodeNullable(final byte[] any) {
        return null; // TODO
    }

    private ChannelType findChannelType(final int type) {
        // @formatter:off
        return Arrays.stream(ChannelType.values())
                       .filter(x -> x.getValue() == type)
                       .findAny()
                       .orElseThrow(
                           () -> new IllegalArgumentException(format("Don't know this channel type %s!", type)));
        // @formatter:on
    }
}
