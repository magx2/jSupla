package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ChannelNewValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.RegisterDeviceResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ServerDeviceSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceSerializerImpl
        implements ServerDeviceSerializer<ServerDeviceEntity, ServerDevice> {
    private final ChannelNewValueSerializer channelNewValueSerializer;
    private final FirmwareUpdateUrlResultSerializer firmwareUpdateUrlResultSerializer;
    private final RegisterDeviceResultSerializer registerDeviceResultSerializer;

    public ServerDeviceSerializerImpl(final ChannelNewValueSerializer channelNewValueSerializer,
                                      final FirmwareUpdateUrlResultSerializer firmwareUpdateUrlResultSerializer,
                                      final RegisterDeviceResultSerializer registerDeviceResultSerializer) {
        this.channelNewValueSerializer = requireNonNull(channelNewValueSerializer);
        this.firmwareUpdateUrlResultSerializer = requireNonNull(firmwareUpdateUrlResultSerializer);
        this.registerDeviceResultSerializer = requireNonNull(registerDeviceResultSerializer);
    }

    @Override
    public ServerDevice serialize(@NotNull final ServerDeviceEntity entity) {
        if (entity instanceof ChannelNewValue) {
            return channelNewValueSerializer.serialize((ChannelNewValue) entity);
        } else if (entity instanceof FirmwareUpdateUrlResult) {
            return firmwareUpdateUrlResultSerializer.serialize((FirmwareUpdateUrlResult) entity);
        } else if (entity instanceof RegisterDeviceResult) {
            return registerDeviceResultSerializer.serialize((RegisterDeviceResult) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
