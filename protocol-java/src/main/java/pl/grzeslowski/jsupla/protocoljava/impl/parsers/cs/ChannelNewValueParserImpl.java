package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Deprecated
public class ChannelNewValueParserImpl implements ChannelNewValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;

    public ChannelNewValueParserImpl(final ChannelTypeDecoder channelTypeDecoder) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
    }

    @Override
    public ChannelNewValue parse(@NotNull final SuplaChannelNewValue proto) {
        return new ChannelNewValue(proto.channelId, channelTypeDecoder.decode(proto));
    }
}
