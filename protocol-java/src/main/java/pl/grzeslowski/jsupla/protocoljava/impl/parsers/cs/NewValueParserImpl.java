package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.NewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.NewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class NewValueParserImpl implements NewValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final DeviceChannelValueParser.TypeMapper typeMapper;

    public NewValueParserImpl(ChannelTypeDecoder channelTypeDecoder, DeviceChannelValueParser.TypeMapper typeMapper) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.typeMapper = requireNonNull(typeMapper);
    }


    @Override
    public NewValue parse(@NotNull SuplaNewValue proto) {
        return new NewValue(
                proto.id,
                proto.target,
                channelTypeDecoder.decode(typeMapper.findChannelType(proto.id), proto.value)
        );
    }
}
