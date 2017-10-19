package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@SuppressWarnings("WeakerAccess")
public class RegisterClientBSerializerImplTest extends SerializerTest<RegisterClientB, SuplaRegisterClientB> {
    @InjectMocks RegisterClientBSerializerImpl serializer;
    @Mock StringSerializer stringSerializer;

    @Override
    protected RegisterClientB given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @Override
    protected void then(final RegisterClientB entity, final SuplaRegisterClientB proto) {
        assertThat(proto.accessId).isEqualTo(entity.getAccessId());
        verify(stringSerializer).serializePassword(entity.getAccessIdPassword(), SUPLA_ACCESSID_PWD_MAXSIZE);
        verify(stringSerializer).serialize(entity.getGuid(), SUPLA_GUID_SIZE);
        verify(stringSerializer).serialize(entity.getName(), SUPLA_CLIENT_NAME_MAXSIZE);
        verify(stringSerializer).serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE);
        verify(stringSerializer).serialize(entity.getServerName(), SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    protected Serializer<RegisterClientB, SuplaRegisterClientB> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterClientB> entityClass() {
        return RegisterClientB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new RegisterClientBSerializerImpl(null);
    }
}
