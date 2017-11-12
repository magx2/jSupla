package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.GetVersionResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.PingServerResultClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.ServerDeviceClientEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.SetActivityTimeoutResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.VersionErrorSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceClientSerializerImpl
        implements ServerDeviceClientEntitySerializer<ServerDeviceClientEntity, ServerDeviceClient> {
    private final GetVersionResultSerializer getVersionResultSerializer;
    private final PingServerResultClientSerializer pingServerResultClientSerializer;
    private final SetActivityTimeoutResultSerializer setActivityTimeoutResultSerializer;
    private final VersionErrorSerializer versionErrorSerializer;

    public ServerDeviceClientSerializerImpl(final GetVersionResultSerializer getVersionResultSerializer,
                                            final PingServerResultClientSerializer pingServerResultClientSerializer,
                                            final SetActivityTimeoutResultSerializer setActivityTimeoutResultSerializer,
                                            final VersionErrorSerializer versionErrorSerializer) {
        this.getVersionResultSerializer = requireNonNull(getVersionResultSerializer);
        this.pingServerResultClientSerializer = requireNonNull(pingServerResultClientSerializer);
        this.setActivityTimeoutResultSerializer = requireNonNull(setActivityTimeoutResultSerializer);
        this.versionErrorSerializer = requireNonNull(versionErrorSerializer);
    }

    @Override
    public ServerDeviceClient serialize(@NotNull final ServerDeviceClientEntity entity) {
        if (entity instanceof GetVersionResult) {
            return getVersionResultSerializer.serialize((GetVersionResult) entity);
        } else if (entity instanceof VersionError) {
            return versionErrorSerializer.serialize((VersionError) entity);
        } else if (entity instanceof SetActivityTimeoutResult) {
            return setActivityTimeoutResultSerializer.serialize((SetActivityTimeoutResult) entity);
        } else if (entity instanceof PingServerResultClient) {
            return pingServerResultClientSerializer.serialize((PingServerResultClient) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
