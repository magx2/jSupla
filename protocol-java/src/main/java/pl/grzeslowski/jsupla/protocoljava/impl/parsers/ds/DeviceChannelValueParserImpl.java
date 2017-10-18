package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelValueParserImpl implements DeviceChannelValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;
    private final SuplaDeviceChannelValueToChannelType suplaDeviceChannelValueToChannelType;

    public DeviceChannelValueParserImpl(final ChannelTypeDecoder channelTypeDecoder,
                                        final SuplaDeviceChannelValueToChannelType
                                                suplaDeviceChannelValueToChannelType) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
        this.suplaDeviceChannelValueToChannelType = requireNonNull(suplaDeviceChannelValueToChannelType);
    }

    @Override
    public DeviceChannelValue parse(@NotNull final SuplaDeviceChannelValue proto) {
        final ChannelType channelType = suplaDeviceChannelValueToChannelType.toChannelType(proto);
        return new DeviceChannelValue(proto.channelNumber, channelTypeDecoder.decode(channelType, proto.value));
    }
}
