package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelsB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceCParser;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class RegisterDeviceCParserImpl implements RegisterDeviceCParser {
    private final StringParser stringParser;
    private final DeviceChannelBParser deviceChannelBParser;

    public RegisterDeviceCParserImpl(final StringParser stringParser, final DeviceChannelBParser deviceChannelBParser) {
        this.stringParser = requireNonNull(stringParser);
        this.deviceChannelBParser = requireNonNull(deviceChannelBParser);
    }

    @Override
    public RegisterDeviceC parse(@NotNull final SuplaRegisterDeviceC proto) {
        final List<DeviceChannelB> channels = Arrays.stream(proto.channels)
            .map(deviceChannelBParser::parse)
            .collect(Collectors.toList());
        return new RegisterDeviceC(
            proto.locationId,
            stringParser.parsePassword(proto.locationPwd),
            stringParser.parseHexString(proto.guid),
            stringParser.parse(proto.name),
            stringParser.parse(proto.softVer),
            new DeviceChannelsB(channels),
            stringParser.parse(proto.serverName));
    }
}
