package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@SuppressWarnings("WeakerAccess")
public class GetVersionResultSerializerImplTest extends SerializerTest<GetVersionResult, SuplaGetVersionResult> {
    @InjectMocks GetVersionResultSerializerImpl serializer;
    @Mock StringSerializer stringSerializer;

    @Override
    protected GetVersionResult given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @Override
    protected void then(final GetVersionResult entity, final SuplaGetVersionResult proto) {
        assertThat((int) proto.protoVersionMin).isEqualTo(entity.getProtoVersionMin());
        assertThat((int) proto.protoVersion).isEqualTo(entity.getProtoVersion());
        verify(stringSerializer).serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE);
    }

    @Override
    protected Serializer<GetVersionResult, SuplaGetVersionResult> serializer() {
        return serializer;
    }

    @Override
    protected Class<GetVersionResult> entityClass() {
        return GetVersionResult.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new GetVersionResultSerializerImpl(null);
    }
}