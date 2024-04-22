package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.RegisterDeviceResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceParserImpl implements ServerDeviceParser<ServerDeviceEntity, ServerDevice> {

    private final ChannelNewValueParser channelNewValueParser;
    private final FirmwareUpdateUrlResultParser firmwareUpdateUrlResultParser;
    private final RegisterDeviceResultParser registerDeviceResultParser;

    public ServerDeviceParserImpl(final ChannelNewValueParser channelNewValueParser,
                                  final FirmwareUpdateUrlResultParser firmwareUpdateUrlResultParser,
                                  final RegisterDeviceResultParser registerDeviceResultParser) {
        this.channelNewValueParser = requireNonNull(channelNewValueParser);
        this.firmwareUpdateUrlResultParser = requireNonNull(firmwareUpdateUrlResultParser);
        this.registerDeviceResultParser = requireNonNull(registerDeviceResultParser);
    }

    @Override
    public ServerDeviceEntity parse(@NotNull final ServerDevice proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaChannelNewValue) {
            return channelNewValueParser.parse((SuplaChannelNewValue) proto);
        } else if (proto instanceof SuplaFirmwareUpdateUrlResult) {
            return firmwareUpdateUrlResultParser.parse((SuplaFirmwareUpdateUrlResult) proto);
        } else if (proto instanceof SuplaRegisterDeviceResult) {
            return registerDeviceResultParser.parse((SuplaRegisterDeviceResult) proto);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to parser! %s",
            proto.getClass(), proto));
    }
}
