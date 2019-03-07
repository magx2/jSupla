package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.VersionErrorSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

public class VersionErrorSerializerImpl implements VersionErrorSerializer {
    public static final VersionErrorSerializerImpl INSTANCE = new VersionErrorSerializerImpl();

    @SuppressWarnings("WeakerAccess")
    VersionErrorSerializerImpl() {
    }

    @Override
    public SuplaVersionError serialize(@NotNull final VersionError entity) {
        return new SuplaVersionError(
                                            (short) entity.getServerVersionMin(),
                                            (short) entity.getServerVersion()
        );
    }
}
