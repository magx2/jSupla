package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class RegisterDeviceResultSerializerImplTest
        extends SerializerTest<RegisterDeviceResult, SuplaRegisterDeviceResult> {
    @InjectMocks RegisterDeviceResultSerializerImpl serializer;

    @Override
    protected void then(final RegisterDeviceResult entity, final SuplaRegisterDeviceResult proto) {
        assertThat(proto.resultCode).isEqualTo(entity.getResultCode());
        assertThat((int) proto.activityTimeout).isEqualTo(entity.getActivityTimeout());
        assertThat((int) proto.version).isEqualTo(entity.getVersion());
        assertThat((int) proto.versionMin).isEqualTo(entity.getVersionMin());
    }

    @Override
    protected Serializer<RegisterDeviceResult, SuplaRegisterDeviceResult> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterDeviceResult> entityClass() {
        return RegisterDeviceResult.class;
    }
}