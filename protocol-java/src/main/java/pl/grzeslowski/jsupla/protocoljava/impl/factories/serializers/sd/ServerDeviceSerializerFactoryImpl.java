package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sd.ServerDeviceSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ChannelNewValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.RegisterDeviceResultSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceSerializerFactoryImpl implements ServerDeviceSerializerFactory {
    private final ChannelNewValueSerializer channelNewValueSerializer;
    private final FirmwareUpdateUrlResultSerializer firmwareUpdateUrlResultSerializer;
    private final RegisterDeviceResultSerializer registerDeviceResultSerializer;

    public ServerDeviceSerializerFactoryImpl(final ChannelNewValueSerializer channelNewValueSerializer,
                                             final FirmwareUpdateUrlResultSerializer firmwareUpdateUrlResultSerializer,
                                             final RegisterDeviceResultSerializer registerDeviceResultSerializer) {
        this.channelNewValueSerializer = requireNonNull(channelNewValueSerializer);
        this.firmwareUpdateUrlResultSerializer = requireNonNull(firmwareUpdateUrlResultSerializer);
        this.registerDeviceResultSerializer = requireNonNull(registerDeviceResultSerializer);
    }

    @Override
    public Serializer<? extends ServerDeviceEntity,
                             ? extends ServerDevice> getSerializer(@NotNull final ServerDeviceEntity entity) {
        if (entity instanceof ChannelNewValue) {
            return channelNewValueSerializer;
        } else if (entity instanceof FirmwareUpdateUrlResult) {
            return firmwareUpdateUrlResultSerializer;
        } else if (entity instanceof RegisterDeviceResult) {
            return registerDeviceResultSerializer;
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
