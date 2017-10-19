package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class RegisterClientResultSerializerImplTest
        extends SerializerTest<RegisterClientResult, SuplaRegisterClientResult> {
    @InjectMocks RegisterClientResultSerializerImpl serializer;

    @Override
    protected void then(final RegisterClientResult entity, final SuplaRegisterClientResult proto) {
        assertThat(proto.resultCode).isEqualTo(entity.getResultCode());
        assertThat(proto.clientId).isEqualTo(entity.getClientId());
        assertThat(proto.locationCount).isEqualTo(entity.getLocationCount());
        assertThat(proto.channelCount).isEqualTo(entity.getChannelCount());
        assertThat((int) proto.activityTimeout).isEqualTo(entity.getActivityTimeout());
        assertThat((int) proto.version).isEqualTo(entity.getVersion());
        assertThat((int) proto.versionMin).isEqualTo(entity.getVersionMin());
    }

    @Override
    protected Serializer<RegisterClientResult, SuplaRegisterClientResult> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterClientResult> entityClass() {
        return RegisterClientResult.class;
    }
}