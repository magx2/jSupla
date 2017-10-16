package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.VersionErrorSerializer;

import javax.validation.constraints.NotNull;

public class VersionErrorSerializerImpl implements VersionErrorSerializer {
    @Override
    public SuplaVersionError serialize(@NotNull final VersionError entity) {
        return new SuplaVersionError(
                                            (short) entity.getServerVersionMin(),
                                            (short) entity.getServerVersion()
        );
    }
}
