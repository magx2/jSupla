package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelNewValueBParserImpl implements ChannelNewValueBParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final DeviceChannelValueParser.TypeMapper typeMapper;

    public ChannelNewValueBParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                      final DeviceChannelValueParser.TypeMapper typeMapper) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.typeMapper = requireNonNull(typeMapper);
    }

    @Override
    public ChannelNewValueB parse(@NotNull final SuplaChannelNewValueB proto) {
        final ChannelType channelType = typeMapper.findChannelType(proto.channelId);
        return new ChannelNewValueB(proto.channelId, channelTypeDecoder.decode(channelType, proto.value));
    }
}
