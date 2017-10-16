package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelValueParserImpl implements ChannelValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;

    public ChannelValueParserImpl(final ChannelTypeDecoder channelTypeDecoder) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
    }

    @Override
    public ChannelValue parse(@NotNull final SuplaChannelValue proto) {
        return new ChannelValue(
                                       channelTypeDecoder.decode(proto.value),
                                       channelTypeDecoder.decodeNullable(proto.subValue)
        );
    }
}
