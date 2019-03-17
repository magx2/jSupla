package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceDParser;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class RegisterDeviceDParserImpl implements RegisterDeviceDParser {
    private final StringParser stringParser;
    private final DeviceChannelBParser deviceChannelBParser;

    public RegisterDeviceDParserImpl(StringParser stringParser, DeviceChannelBParser deviceChannelBParser) {
        this.stringParser = requireNonNull(stringParser);
        this.deviceChannelBParser = requireNonNull(deviceChannelBParser);
    }

    @Override
    public RegisterDeviceD parse(@NotNull SuplaRegisterDeviceD proto) {
        List<DeviceChannelB> channels = new ArrayList<>(proto.channelCount);
        for (SuplaDeviceChannelB channel : proto.channels) {
            DeviceChannelB parse = deviceChannelBParser.parse(channel);
            channels.add(parse);
        }
        return new RegisterDeviceD(
                stringParser.parse(proto.email),
                stringParser.parse(proto.authKey),
                stringParser.parseHexString(proto.guid),
                stringParser.parse(proto.name),
                stringParser.parse(proto.softVer),
                stringParser.parse(proto.serverName),
                channels
        );
    }
}
