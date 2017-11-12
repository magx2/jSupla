package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceClientServerSerializerImpl
        implements DeviceClientServerSerializer<DeviceClientServerEntity, DeviceClientServer> {
    private final PingServerSerializer pingServerSerializer;
    private final SetActivityTimeoutSerializer setActivityTimeoutSerializer;

    public DeviceClientServerSerializerImpl(final PingServerSerializer pingServerSerializer,
                                            final SetActivityTimeoutSerializer setActivityTimeoutSerializer) {
        this.pingServerSerializer = requireNonNull(pingServerSerializer);
        this.setActivityTimeoutSerializer = requireNonNull(setActivityTimeoutSerializer);
    }

    @Override
    public DeviceClientServer serialize(@NotNull final DeviceClientServerEntity entity) {
        if (entity instanceof PingServer) {
            return pingServerSerializer.serialize((PingServer) entity);
        } else if (entity instanceof SetActivityTimeout) {
            return setActivityTimeoutSerializer.serialize((SetActivityTimeout) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
