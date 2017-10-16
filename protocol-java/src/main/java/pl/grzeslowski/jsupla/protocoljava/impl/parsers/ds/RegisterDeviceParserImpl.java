package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannels;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceParser;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Deprecated
public class RegisterDeviceParserImpl implements RegisterDeviceParser {
    private final StringParser stringParser;
    private final DeviceChannelParser deviceChannelParser;

    public RegisterDeviceParserImpl(final StringParser stringParser, final DeviceChannelParser deviceChannelParser) {
        this.stringParser = requireNonNull(stringParser);
        this.deviceChannelParser = requireNonNull(deviceChannelParser);
    }

    @Override
    public RegisterDevice parse(@NotNull final SuplaRegisterDevice proto) {
        final Set<DeviceChannel> channels = Arrays.stream(proto.channels)
                                                    .map(deviceChannelParser::parse)
                                                    .collect(Collectors.toSet());
        return new RegisterDevice(
                                         proto.locationId,
                                         stringParser.parsePassword(proto.locationPwd),
                                         stringParser.parse(proto.guid),
                                         stringParser.parse(proto.name),
                                         stringParser.parse(proto.softVer),
                                         proto.channelCount,
                                         new DeviceChannels(channels)
        );
    }
}
