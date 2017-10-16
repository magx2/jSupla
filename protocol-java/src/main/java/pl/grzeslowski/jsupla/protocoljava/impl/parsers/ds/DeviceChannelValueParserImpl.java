package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelValueParserImpl implements DeviceChannelValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;

    public DeviceChannelValueParserImpl(final ChannelTypeDecoder channelTypeDecoder) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
    }

    @Override
    public DeviceChannelValue parse(@NotNull final SuplaDeviceChannelValue proto) {
        return new DeviceChannelValue(proto.channelNumber, channelTypeDecoder.decode(proto));
    }
}
