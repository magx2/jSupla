package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers;

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
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.cs.ClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.dcs.DeviceClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.ds.DeviceServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sc.ServerClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sd.ServerDeviceSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sdc.ServerDeviceClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class SerializerFactoryImpl implements SerializerFactory<Entity, Proto> {
    private final ClientServerSerializerFactory clientServerSerializerFactory;
    private final DeviceClientServerSerializerFactory deviceClientServerSerializerFactory;
    private final DeviceServerSerializerFactory deviceServerSerializerFactory;
    private final ServerClientSerializerFactory serverClientSerializerFactory;
    private final ServerDeviceSerializerFactory serverDeviceSerializerFactory;
    private final ServerDeviceClientSerializerFactory serverDeviceClientSerializerFactory;

    private final DeviceChannelSerializer deviceChannelSerializer;
    private final DeviceChannelBSerializer deviceChannelBSerializer;
    private final FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;
    private final ChannelValueSerializer channelValueSerializer;
    private final TimevalSerializer timevalSerializer;

    public SerializerFactoryImpl(final ClientServerSerializerFactory clientServerSerializerFactory,
                                 final DeviceClientServerSerializerFactory deviceClientServerSerializerFactory,
                                 final DeviceServerSerializerFactory deviceServerSerializerFactory,
                                 final ServerClientSerializerFactory serverClientSerializerFactory,
                                 final ServerDeviceSerializerFactory serverDeviceSerializerFactory,
                                 final ServerDeviceClientSerializerFactory serverDeviceClientSerializerFactory,
                                 final DeviceChannelSerializer deviceChannelSerializer,
                                 final DeviceChannelBSerializer deviceChannelBSerializer,
                                 final FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer,
                                 final ChannelValueSerializer channelValueSerializer,
                                 final TimevalSerializer timevalSerializer) {
        this.clientServerSerializerFactory = requireNonNull(clientServerSerializerFactory);
        this.deviceClientServerSerializerFactory = requireNonNull(deviceClientServerSerializerFactory);
        this.deviceServerSerializerFactory = requireNonNull(deviceServerSerializerFactory);
        this.serverClientSerializerFactory = requireNonNull(serverClientSerializerFactory);
        this.serverDeviceSerializerFactory = requireNonNull(serverDeviceSerializerFactory);
        this.serverDeviceClientSerializerFactory = requireNonNull(serverDeviceClientSerializerFactory);
        this.deviceChannelSerializer = requireNonNull(deviceChannelSerializer);
        this.deviceChannelBSerializer = requireNonNull(deviceChannelBSerializer);
        this.firmwareUpdateUrlSerializer = requireNonNull(firmwareUpdateUrlSerializer);
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
        this.timevalSerializer = requireNonNull(timevalSerializer);
    }

    @Override
    public Serializer<? extends Entity, ? extends Proto> getSerializer(final Entity entity) {
        if (entity instanceof ClientServerEntity) {
            return clientServerSerializerFactory.getSerializer((ClientServerEntity) entity);
        } else if (entity instanceof DeviceClientServerEntity) {
            return deviceClientServerSerializerFactory.getSerializer((DeviceClientServerEntity) entity);
        } else if (entity instanceof DeviceServerEntity) {
            return deviceServerSerializerFactory.getSerializer((DeviceServerEntity) entity);
        } else if (entity instanceof ServerClientEntity) {
            return serverClientSerializerFactory.getSerializer((ServerClientEntity) entity);
        } else if (entity instanceof ServerDeviceEntity) {
            return serverDeviceSerializerFactory.getSerializer((ServerDeviceEntity) entity);
        } else if (entity instanceof ServerDeviceClientEntity) {
            return serverDeviceClientSerializerFactory.getSerializer((ServerDeviceClientEntity) entity);
        }
        return commonClasses(entity);
    }

    private Serializer<? extends Entity, ? extends Proto> commonClasses(final Entity entity) {
        if (entity instanceof DeviceChannelB) {
            return deviceChannelBSerializer;
        } else if (entity instanceof DeviceChannel) {
            return deviceChannelSerializer;
        } else if (entity instanceof FirmwareUpdateUrl) {
            return firmwareUpdateUrlSerializer;
        } else if (entity instanceof ChannelValue) {
            return channelValueSerializer;
        } else if (entity instanceof Timeval) {
            return timevalSerializer;
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
