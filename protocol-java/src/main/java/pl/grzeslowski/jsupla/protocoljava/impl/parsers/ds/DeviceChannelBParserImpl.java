package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelBParserImpl implements DeviceChannelBParser {
    private final ChannelTypeDecoder channelTypeDecoder;

    public DeviceChannelBParserImpl(final ChannelTypeDecoder channelTypeDecoder) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
    }

    @Override
    public DeviceChannelB parse(@NotNull final SuplaDeviceChannelB proto) {
        return new DeviceChannelB(
                                         proto.number,
                                         proto.type,
                                         channelTypeDecoder.decode(proto),
                                         proto.funcList,
                                         proto.defaultValue
        );
    }
}
