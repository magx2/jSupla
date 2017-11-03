package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sd.ServerDeviceParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.RegisterDeviceResultParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceParserFactoryImpl implements ServerDeviceParserFactory {

    private final ChannelNewValueParser channelNewValueParser;
    private final FirmwareUpdateUrlResultParser firmwareUpdateUrlResultParser;
    private final RegisterDeviceResultParser registerDeviceResultParser;

    public ServerDeviceParserFactoryImpl(final ChannelNewValueParser channelNewValueParser,
                                         final FirmwareUpdateUrlResultParser firmwareUpdateUrlResultParser,
                                         final RegisterDeviceResultParser registerDeviceResultParser) {
        this.channelNewValueParser = requireNonNull(channelNewValueParser);
        this.firmwareUpdateUrlResultParser = requireNonNull(firmwareUpdateUrlResultParser);
        this.registerDeviceResultParser = requireNonNull(registerDeviceResultParser);
    }

    @Override
    public Parser<? extends ServerDeviceEntity, ? extends ServerDevice> getParser(@NotNull final ServerDevice proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaChannelNewValue) {
            return channelNewValueParser;
        } else if (proto instanceof SuplaFirmwareUpdateUrlResult) {
            return firmwareUpdateUrlResultParser;
        } else if (proto instanceof SuplaRegisterDeviceResult) {
            return registerDeviceResultParser;
        }
        throw new IllegalArgumentException(format("Don't know this type of proto. Class name: %s.",
                proto.getClass().getSimpleName()));
    }
}
