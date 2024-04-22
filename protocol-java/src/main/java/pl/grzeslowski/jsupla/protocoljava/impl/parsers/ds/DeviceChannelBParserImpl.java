package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelBToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelBParserImpl implements DeviceChannelBParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final SuplaDeviceChannelBToChannelType suplaDeviceChannelBToChannelType;

    public DeviceChannelBParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                    final SuplaDeviceChannelBToChannelType suplaDeviceChannelBToChannelType) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.suplaDeviceChannelBToChannelType = requireNonNull(suplaDeviceChannelBToChannelType);
    }

    @Override
    public DeviceChannelB parse(@NotNull final SuplaDeviceChannelB proto) {
        final ChannelType channelType = suplaDeviceChannelBToChannelType.toChannelType(proto);
        return new DeviceChannelB(
            proto.number,
            proto.type,
            channelTypeDecoder.decode(channelType, proto.value),
            proto.funcList,
            proto.defaultValue
        );
    }
}
