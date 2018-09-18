package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelValueParserImpl implements DeviceChannelValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final TypeMapper typeMapper;

    public DeviceChannelValueParserImpl(
            final ChannelTypeDecoder channelTypeDecoder,
            final TypeMapper typeMapper) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.typeMapper = requireNonNull(typeMapper);
    }

    @Override
    public DeviceChannelValue parse(@NotNull final SuplaDeviceChannelValue proto) {
        final ChannelType channelType = typeMapper.findChannelType(proto.channelNumber);
        return new DeviceChannelValue(proto.channelNumber, channelTypeDecoder.decode(channelType, proto.value));
    }
}
