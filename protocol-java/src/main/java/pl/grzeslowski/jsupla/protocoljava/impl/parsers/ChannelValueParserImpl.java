package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelValueParserImpl implements ChannelValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final SuplaChannelValueToChannelType suplaChannelValueToChannelType;

    public ChannelValueParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                  final SuplaChannelValueToChannelType suplaChannelValueToChannelType) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.suplaChannelValueToChannelType = requireNonNull(suplaChannelValueToChannelType);
    }

    @Override
    public ChannelValue parse(@NotNull final SuplaChannelValue proto) {
        final ChannelType channelType = suplaChannelValueToChannelType.toChannelType(proto);
        final ChannelType channelTypeSubValue = suplaChannelValueToChannelType.toChannelTypeSubValue(proto);
        return new ChannelValue(
            channelTypeDecoder.decode(channelType, proto.value),
            decodeSubValue(proto, channelTypeSubValue)
        );
    }

    // @formatter:off
    @Valid
    private pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue
        decodeSubValue(
                          final @NotNull SuplaChannelValue proto,
                          final ChannelType channelTypeSubValue) {
        if (channelTypeSubValue != null) {
            return channelTypeDecoder.decode(channelTypeSubValue, proto.subValue);
        } else {
            return null;
        }
    }
    // @formatter:on
}
