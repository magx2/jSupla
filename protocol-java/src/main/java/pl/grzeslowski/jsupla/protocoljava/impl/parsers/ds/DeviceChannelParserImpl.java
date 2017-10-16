package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Deprecated
public class DeviceChannelParserImpl implements DeviceChannelParser {
    private final ChannelTypeDecoder channelTypeDecoder;

    public DeviceChannelParserImpl(final ChannelTypeDecoder channelTypeDecoder) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
    }

    @Override
    public DeviceChannel parse(@NotNull final SuplaDeviceChannel proto) {
        return new DeviceChannel(proto.number, proto.type, channelTypeDecoder.decode(proto));
    }
}
