package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelsB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceBParser;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Deprecated
public class RegisterDeviceBParserImpl implements RegisterDeviceBParser {
    private final StringParser stringParser;
    private final DeviceChannelBParser deviceChannelBParser;

    public RegisterDeviceBParserImpl(final StringParser stringParser, final DeviceChannelBParser deviceChannelBParser) {
        this.stringParser = requireNonNull(stringParser);
        this.deviceChannelBParser = requireNonNull(deviceChannelBParser);
    }

    @Override
    public RegisterDeviceB parse(@NotNull final SuplaRegisterDeviceB proto) {
        final List<DeviceChannelB> channels = Arrays.stream(proto.channels)
                                                     .map(deviceChannelBParser::parse)
                .collect(Collectors.toList());
        return new RegisterDeviceB(
                                          proto.locationId,
                                          stringParser.parsePassword(proto.locationPwd),
                stringParser.parseHexString(proto.guid),
                                          stringParser.parse(proto.name),
                                          stringParser.parse(proto.softVer),
                                          new DeviceChannelsB(channels));
    }
}
