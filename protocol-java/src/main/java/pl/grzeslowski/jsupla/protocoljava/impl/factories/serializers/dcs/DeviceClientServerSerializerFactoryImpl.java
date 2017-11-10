package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.dcs.DeviceClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceClientServerSerializerFactoryImpl implements DeviceClientServerSerializerFactory {
    private final PingServerSerializer pingServerSerializer;
    private final SetActivityTimeoutSerializer setActivityTimeoutSerializer;

    public DeviceClientServerSerializerFactoryImpl(final PingServerSerializer pingServerSerializer,
                                                   final SetActivityTimeoutSerializer setActivityTimeoutSerializer) {
        this.pingServerSerializer = requireNonNull(pingServerSerializer);
        this.setActivityTimeoutSerializer = requireNonNull(setActivityTimeoutSerializer);
    }

    @Override
    public Serializer<? extends DeviceClientServerEntity,
                             ? extends DeviceClientServer> getSerializer(@NotNull DeviceClientServerEntity entity) {
        if (entity instanceof PingServer) {
            return pingServerSerializer;
        } else if (entity instanceof SetActivityTimeout) {
            return setActivityTimeoutSerializer;
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
