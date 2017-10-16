package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelNewValueBParserImpl implements ChannelNewValueBParser {
    private final ChannelTypeDecoder channelTypeDecoder;

    public ChannelNewValueBParserImpl(final ChannelTypeDecoder channelTypeDecoder) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
    }

    @Override
    public ChannelNewValueB parse(@NotNull final SuplaChannelNewValueB proto) {
        return new ChannelNewValueB(proto.channelId, channelTypeDecoder.decode(proto));
    }
}
