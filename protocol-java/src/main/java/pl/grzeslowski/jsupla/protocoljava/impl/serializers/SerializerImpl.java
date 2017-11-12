package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ServerClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ServerDeviceSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.ServerDeviceClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class SerializerImpl implements Serializer<Entity, Proto> {
    private final ClientServerSerializer<ClientServerEntity, ClientServer> clientServerSerializer;
    private final DeviceClientServerSerializer<DeviceClientServerEntity, DeviceClientServer>
            deviceClientServerSerializer;
    private final DeviceServerSerializer<DeviceServerEntity, DeviceServer> deviceServerSerializer;
    private final ServerClientSerializer<ServerClientEntity, ServerClient> serverClientSerializer;
    private final ServerDeviceSerializer<ServerDeviceEntity, ServerDevice> serverDeviceSerializer;
    private final ServerDeviceClientSerializer<ServerDeviceClientEntity, ServerDeviceClient>
            serverDeviceClientSerializer;

    private final DeviceChannelSerializer deviceChannelSerializer;
    private final DeviceChannelBSerializer deviceChannelBSerializer;
    private final FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;
    private final ChannelValueSerializer channelValueSerializer;
    private final TimevalSerializer timevalSerializer;

    public SerializerImpl(final ClientServerSerializer<ClientServerEntity, ClientServer> clientServerSerializer,
                          final DeviceClientServerSerializer<DeviceClientServerEntity, DeviceClientServer>
                                  deviceClientServerSerializer,
                          final DeviceServerSerializer<DeviceServerEntity, DeviceServer> deviceServerSerializer,
                          final ServerClientSerializer<ServerClientEntity, ServerClient> serverClientSerializer,
                          final ServerDeviceSerializer<ServerDeviceEntity, ServerDevice> serverDeviceSerializer,
                          final ServerDeviceClientSerializer<ServerDeviceClientEntity, ServerDeviceClient>
                                  serverDeviceClientSerializer,
                          final DeviceChannelSerializer deviceChannelSerializer,
                          final DeviceChannelBSerializer deviceChannelBSerializer,
                          final FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer,
                          final ChannelValueSerializer channelValueSerializer,
                          final TimevalSerializer timevalSerializer) {
        this.clientServerSerializer = requireNonNull(clientServerSerializer);
        this.deviceClientServerSerializer = requireNonNull(deviceClientServerSerializer);
        this.deviceServerSerializer = requireNonNull(deviceServerSerializer);
        this.serverClientSerializer = requireNonNull(serverClientSerializer);
        this.serverDeviceSerializer = requireNonNull(serverDeviceSerializer);
        this.serverDeviceClientSerializer = requireNonNull(serverDeviceClientSerializer);

        this.deviceChannelSerializer = requireNonNull(deviceChannelSerializer);
        this.deviceChannelBSerializer = requireNonNull(deviceChannelBSerializer);
        this.firmwareUpdateUrlSerializer = requireNonNull(firmwareUpdateUrlSerializer);
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
        this.timevalSerializer = requireNonNull(timevalSerializer);
    }

    @Override
    public Proto serialize(@NotNull final Entity entity) {
        if (entity instanceof ClientServerEntity) {
            return clientServerSerializer.serialize((ClientServerEntity) entity);
        } else if (entity instanceof DeviceClientServerEntity) {
            return deviceClientServerSerializer.serialize((DeviceClientServerEntity) entity);
        } else if (entity instanceof DeviceServerEntity) {
            return deviceServerSerializer.serialize((DeviceServerEntity) entity);
        } else if (entity instanceof ServerClientEntity) {
            return serverClientSerializer.serialize((ServerClientEntity) entity);
        } else if (entity instanceof ServerDeviceEntity) {
            return serverDeviceSerializer.serialize((ServerDeviceEntity) entity);
        } else if (entity instanceof ServerDeviceClientEntity) {
            return serverDeviceClientSerializer.serialize((ServerDeviceClientEntity) entity);
        }
        return commonClasses(entity);
    }

    private Proto commonClasses(final Entity entity) {
        if (entity instanceof DeviceChannelB) {
            return deviceChannelBSerializer.serialize((DeviceChannelB) entity);
        } else if (entity instanceof DeviceChannel) {
            return deviceChannelSerializer.serialize((DeviceChannel) entity);
        } else if (entity instanceof FirmwareUpdateUrl) {
            return firmwareUpdateUrlSerializer.serialize((FirmwareUpdateUrl) entity);
        } else if (entity instanceof ChannelValue) {
            return channelValueSerializer.serialize((ChannelValue) entity);
        } else if (entity instanceof Timeval) {
            return timevalSerializer.serialize((Timeval) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
