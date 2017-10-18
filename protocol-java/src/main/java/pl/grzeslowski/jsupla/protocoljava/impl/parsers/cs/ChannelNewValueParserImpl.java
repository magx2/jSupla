package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelNewValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Deprecated
public class ChannelNewValueParserImpl implements ChannelNewValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final SuplaChannelNewValueToChannelType suplaChannelNewValueToChannelType;

    public ChannelNewValueParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                     final SuplaChannelNewValueToChannelType suplaChannelNewValueToChannelType) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.suplaChannelNewValueToChannelType = requireNonNull(suplaChannelNewValueToChannelType);
    }

    @Override
    public ChannelNewValue parse(@NotNull final SuplaChannelNewValue proto) {
        final ChannelType channelType = suplaChannelNewValueToChannelType.toChannelType(proto);
        return new ChannelNewValue(
                                          proto.channelId,
                                          channelTypeDecoder.decode(channelType, proto.value));
    }
}
