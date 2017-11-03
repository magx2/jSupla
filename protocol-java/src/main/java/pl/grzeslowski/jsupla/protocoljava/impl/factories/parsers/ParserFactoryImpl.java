package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.cs.ClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.dcs.DeviceClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ds.DeviceServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sc.ServerClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sd.ServerDeviceParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sdc.ServerDeviceClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ParserFactoryImpl implements ParserFactory<Entity, Proto> {
    private final ClientServerParserFactory clientServerParserFactory;
    private final DeviceClientServerParserFactory deviceClientServerParserFactory;
    private final DeviceServerParserFactory deviceServerParserFactory;
    private final ServerClientParserFactory serverClientParserFactory;
    private final ServerDeviceParserFactory serverDeviceParserFactory;
    private final ServerDeviceClientParserFactory serverDeviceClientParserFactory;

    private final DeviceChannelParser deviceChannelParser;
    private final DeviceChannelBParser deviceChannelBParser;
    private final FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    private final ChannelValueParser channelValueParser;
    private final TimevalParser timevalParser;

    public ParserFactoryImpl(final ClientServerParserFactory clientServerParserFactory,
                             final DeviceClientServerParserFactory deviceClientServerParserFactory,
                             final DeviceServerParserFactory deviceServerParserFactory,
                             final ServerClientParserFactory serverClientParserFactory,
                             final ServerDeviceParserFactory serverDeviceParserFactory,
                             final ServerDeviceClientParserFactory serverDeviceClientParserFactory,
                             final DeviceChannelParser deviceChannelParser,
                             final DeviceChannelBParser deviceChannelBParser,
                             final FirmwareUpdateUrlParser firmwareUpdateUrlParser,
                             final ChannelValueParser channelValueParser,
                             final TimevalParser timevalParser) {
        this.clientServerParserFactory = requireNonNull(clientServerParserFactory);
        this.deviceClientServerParserFactory = requireNonNull(deviceClientServerParserFactory);
        this.deviceServerParserFactory = requireNonNull(deviceServerParserFactory);
        this.serverClientParserFactory = requireNonNull(serverClientParserFactory);
        this.serverDeviceParserFactory = requireNonNull(serverDeviceParserFactory);
        this.serverDeviceClientParserFactory = requireNonNull(serverDeviceClientParserFactory);

        this.deviceChannelParser = requireNonNull(deviceChannelParser);
        this.deviceChannelBParser = requireNonNull(deviceChannelBParser);
        this.firmwareUpdateUrlParser = requireNonNull(firmwareUpdateUrlParser);
        this.channelValueParser = requireNonNull(channelValueParser);
        this.timevalParser = requireNonNull(timevalParser);
    }

    @Override
    public Parser<? extends Entity, ? extends Proto> getParser(@NotNull final Proto proto) {
        requireNonNull(proto);
        if (proto instanceof ClientServer) {
            return clientServerParserFactory.getParser((ClientServer) proto);
        } else if (proto instanceof DeviceClientServer) {
            return deviceClientServerParserFactory.getParser((DeviceClientServer) proto);
        } else if (proto instanceof DeviceServer) {
            return deviceServerParserFactory.getParser((DeviceServer) proto);
        } else if (proto instanceof ServerClient) {
            return serverClientParserFactory.getParser((ServerClient) proto);
        } else if (proto instanceof ServerDevice) {
            return serverDeviceParserFactory.getParser((ServerDevice) proto);
        } else if (proto instanceof ServerDeviceClient) {
            return serverDeviceClientParserFactory.getParser((ServerDeviceClient) proto);
        } else {
            return commonProto(proto);
        }
    }

    private Parser<? extends Entity, ? extends Proto> commonProto(final @NotNull Proto proto) {
        if (proto instanceof SuplaDeviceChannel) {
            return deviceChannelParser;
        } else if (proto instanceof SuplaDeviceChannelB) {
            return deviceChannelBParser;
        } else if (proto instanceof SuplaFirmwareUpdateUrl) {
            return firmwareUpdateUrlParser;
        } else if (proto instanceof SuplaChannelValue) {
            return channelValueParser;
        } else if (proto instanceof SuplaTimeval) {
            return timevalParser;
        } else {
            throw new IllegalArgumentException(format("Don't know this proto type %s!",
                    proto.getClass().getSimpleName()));
        }
    }
}
