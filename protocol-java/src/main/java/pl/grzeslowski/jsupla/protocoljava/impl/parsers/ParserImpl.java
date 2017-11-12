package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

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
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ClientServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ServerClientEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.ServerDeviceClientEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ParserImpl implements Parser<Entity, Proto> {
    private final ClientServerEntityParser<ClientServerEntity, ClientServer> clientServerParserFactory;
    private final DeviceClientServerEntityParser<DeviceClientServerEntity, DeviceClientServer>
            deviceClientServerParserFactory;
    private final DeviceServerEntityParser<DeviceServerEntity, DeviceServer> deviceServerParserFactory;
    private final ServerClientEntityParser<ServerClientEntity, ServerClient> serverClientParserFactory;
    private final ServerDeviceEntityParser<ServerDeviceEntity, ServerDevice> serverDeviceParserFactory;
    private final ServerDeviceClientEntityParser<ServerDeviceClientEntity, ServerDeviceClient>
            serverDeviceClientParserFactory;

    private final DeviceChannelParser deviceChannelParser;
    private final DeviceChannelBParser deviceChannelBParser;
    private final FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    private final ChannelValueParser channelValueParser;
    private final TimevalParser timevalParser;

    public ParserImpl(final ClientServerEntityParser<ClientServerEntity, ClientServer> clientServerParserFactory,
                      final DeviceClientServerEntityParser<DeviceClientServerEntity, DeviceClientServer>
                              deviceClientServerParserFactory,
                      final DeviceServerEntityParser<DeviceServerEntity, DeviceServer> deviceServerParserFactory,
                      final ServerClientEntityParser<ServerClientEntity, ServerClient> serverClientParserFactory,
                      final ServerDeviceEntityParser<ServerDeviceEntity, ServerDevice> serverDeviceParserFactory,
                      final ServerDeviceClientEntityParser<ServerDeviceClientEntity, ServerDeviceClient>
                              serverDeviceClientParserFactory,
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
    public Entity parse(@NotNull final Proto proto) {
        requireNonNull(proto);
        if (proto instanceof ClientServer) {
            return clientServerParserFactory.parse((ClientServer) proto);
        } else if (proto instanceof DeviceClientServer) {
            return deviceClientServerParserFactory.parse((DeviceClientServer) proto);
        } else if (proto instanceof DeviceServer) {
            return deviceServerParserFactory.parse((DeviceServer) proto);
        } else if (proto instanceof ServerClient) {
            return serverClientParserFactory.parse((ServerClient) proto);
        } else if (proto instanceof ServerDevice) {
            return serverDeviceParserFactory.parse((ServerDevice) proto);
        } else if (proto instanceof ServerDeviceClient) {
            return serverDeviceClientParserFactory.parse((ServerDeviceClient) proto);
        } else {
            return commonProto(proto);
        }
    }

    private Entity commonProto(final @NotNull Proto proto) {
        if (proto instanceof SuplaDeviceChannel) {
            return deviceChannelParser.parse((SuplaDeviceChannel) proto);
        } else if (proto instanceof SuplaDeviceChannelB) {
            return deviceChannelBParser.parse((SuplaDeviceChannelB) proto);
        } else if (proto instanceof SuplaFirmwareUpdateUrl) {
            return firmwareUpdateUrlParser.parse((SuplaFirmwareUpdateUrl) proto);
        } else if (proto instanceof SuplaChannelValue) {
            return channelValueParser.parse((SuplaChannelValue) proto);
        } else if (proto instanceof SuplaTimeval) {
            return timevalParser.parse((SuplaTimeval) proto);
        } else {
            throw new IllegalArgumentException(format("Don't know this proto type %s!",
                    proto.getClass().getSimpleName()));
        }
    }

}
