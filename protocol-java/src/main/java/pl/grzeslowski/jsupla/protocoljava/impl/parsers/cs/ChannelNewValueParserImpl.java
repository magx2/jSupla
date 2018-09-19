package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Deprecated
public class ChannelNewValueParserImpl implements ChannelNewValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final DeviceChannelValueParser.TypeMapper typeMapper;

    public ChannelNewValueParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                     final DeviceChannelValueParser.TypeMapper typeMapper) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.typeMapper = requireNonNull(typeMapper);
    }

    @Override
    public ChannelNewValue parse(@NotNull final SuplaChannelNewValue proto) {
        final ChannelType channelType = typeMapper.findChannelType(proto.channelId);
        return new ChannelNewValue(
                                          proto.channelId,
                                          channelTypeDecoder.decode(channelType, proto.value));
    }
}
