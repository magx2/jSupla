package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelNewValueBToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelNewValueBParserImpl implements ChannelNewValueBParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final SuplaChannelNewValueBToChannelType suplaChannelNewValueBToChannelType;

    public ChannelNewValueBParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                      final SuplaChannelNewValueBToChannelType suplaChannelNewValueBToChannelType) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.suplaChannelNewValueBToChannelType = requireNonNull(suplaChannelNewValueBToChannelType);
    }

    @Override
    public ChannelNewValueB parse(@NotNull final SuplaChannelNewValueB proto) {
        final ChannelType channelType = suplaChannelNewValueBToChannelType.toChannelType(proto);
        return new ChannelNewValueB(proto.channelId, channelTypeDecoder.decode(channelType, proto.value));
    }
}
