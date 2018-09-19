package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

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
        final ChannelType channelType = typeMapper.findChannelType(proto.channelNumber);
        return new ChannelNewValue(
                                          proto.senderId,
                                          proto.channelNumber,
                                          proto.durationMs,
                                          channelTypeDecoder.decode(channelType, proto.value)
        );
    }
}
