package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResultB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterClientResultBSerializerImplTest extends SerializerTest<RegisterClientResultB, SuplaRegisterClientResultB> {
    @InjectMocks
    RegisterClientResultBSerializerImpl serializer;

    @Override
    protected void then(RegisterClientResultB entity, SuplaRegisterClientResultB proto) {
        assertThat(proto.resultCode).isEqualTo(entity.getResultCode());
        assertThat(proto.clientId).isEqualTo(entity.getClientId());
        assertThat(proto.locationCount).isEqualTo(entity.getLocationCount());
        assertThat(proto.channelCount).isEqualTo(entity.getChannelCount());
        assertThat((int) proto.activityTimeout).isEqualTo(entity.getActivityTimeout());
        assertThat((int) proto.version).isEqualTo(entity.getVersion());
        assertThat((int) proto.versionMin).isEqualTo(entity.getVersionMin());
        assertThat(proto.flags).isEqualTo(entity.getFlags());
    }

    @Override
    protected Serializer<RegisterClientResultB, SuplaRegisterClientResultB> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterClientResultB> entityClass() {
        return RegisterClientResultB.class;
    }
}