package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelParserImpl implements DeviceChannelParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final SuplaDeviceChannelToChannelType suplaDeviceChannelToChannelType;

    public DeviceChannelParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                   final SuplaDeviceChannelToChannelType suplaDeviceChannelToChannelType) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.suplaDeviceChannelToChannelType = requireNonNull(suplaDeviceChannelToChannelType);
    }

    @Override
    public DeviceChannel parse(@NotNull final SuplaDeviceChannel proto) {
        final ChannelType channelType = suplaDeviceChannelToChannelType.toChannelType(proto);
        return new DeviceChannel(proto.number, proto.type, channelTypeDecoder.decode(channelType, proto.value));
    }
}
