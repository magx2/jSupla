package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class VersionErrorSerializerImplTest extends SerializerTest<VersionError, SuplaVersionError> {
    @InjectMocks
    VersionErrorSerializerImpl serializer;

    @Override
    protected void then(final VersionError entity, final SuplaVersionError proto) {
        assertThat((int) proto.serverVersion).isEqualTo(entity.getServerVersion());
        assertThat((int) proto.serverVersionMin).isEqualTo(entity.getServerVersionMin());
    }

    @Override
    protected Serializer<VersionError, SuplaVersionError> serializer() {
        return serializer;
    }

    @Override
    protected Class<VersionError> entityClass() {
        return VersionError.class;
    }
}