package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sdc.ServerDeviceClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.GetVersionResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.PingServerResultClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.SetActivityTimeoutResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.VersionErrorSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceClientSerializerFactoryImpl implements ServerDeviceClientSerializerFactory {
    private final GetVersionResultSerializer getVersionResultSerializer;
    private final PingServerResultClientSerializer pingServerResultClientSerializer;
    private final SetActivityTimeoutResultSerializer setActivityTimeoutResultSerializer;
    private final VersionErrorSerializer versionErrorSerializer;

    public ServerDeviceClientSerializerFactoryImpl(final GetVersionResultSerializer getVersionResultSerializer,
                                                   final PingServerResultClientSerializer
                                                           pingServerResultClientSerializer,
                                                   final SetActivityTimeoutResultSerializer
                                                           setActivityTimeoutResultSerializer,
                                                   final VersionErrorSerializer versionErrorSerializer) {
        this.getVersionResultSerializer = requireNonNull(getVersionResultSerializer);
        this.pingServerResultClientSerializer = requireNonNull(pingServerResultClientSerializer);
        this.setActivityTimeoutResultSerializer = requireNonNull(setActivityTimeoutResultSerializer);
        this.versionErrorSerializer = requireNonNull(versionErrorSerializer);
    }

    @Override
    public Serializer<? extends ServerDeviceClientEntity,
                             ? extends ServerDeviceClient> getSerializer(@NotNull ServerDeviceClientEntity entity) {
        if (entity instanceof GetVersionResult) {
            return getVersionResultSerializer;
        } else if (entity instanceof VersionError) {
            return versionErrorSerializer;
        } else if (entity instanceof SetActivityTimeoutResult) {
            return setActivityTimeoutResultSerializer;
        } else if (entity instanceof PingServerResultClient) {
            return pingServerResultClientSerializer;
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
