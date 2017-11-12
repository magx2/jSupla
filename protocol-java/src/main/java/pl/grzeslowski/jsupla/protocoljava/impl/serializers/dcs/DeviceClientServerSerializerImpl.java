package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutEntitySerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceClientServerSerializerImpl
        implements DeviceClientServerEntitySerializer<DeviceClientServerEntity, DeviceClientServer> {
    private final PingServerEntitySerializer pingServerSerializer;
    private final SetActivityTimeoutEntitySerializer setActivityTimeoutSerializer;

    public DeviceClientServerSerializerImpl(final PingServerEntitySerializer pingServerSerializer,
                                            final SetActivityTimeoutEntitySerializer setActivityTimeoutSerializer) {
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
