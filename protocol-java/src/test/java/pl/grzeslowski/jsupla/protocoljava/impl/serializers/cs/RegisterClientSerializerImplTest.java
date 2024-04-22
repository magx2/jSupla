package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@SuppressWarnings("WeakerAccess")
public class RegisterClientSerializerImplTest extends SerializerTest<RegisterClient, SuplaRegisterClient> {
    @InjectMocks
    RegisterClientSerializerImpl serializer;
    @Mock
    StringSerializer stringSerializer;

    @Override
    protected RegisterClient given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @Override
    protected void then(final RegisterClient entity, final SuplaRegisterClient proto) {
        assertThat(proto.accessId).isEqualTo(entity.getAccessId());
        verify(stringSerializer).serializePassword(entity.getAccessIdPassword(), SUPLA_ACCESSID_PWD_MAXSIZE);
        verify(stringSerializer).serialize(entity.getGuid(), SUPLA_GUID_SIZE);
        verify(stringSerializer).serialize(entity.getName(), SUPLA_CLIENT_NAME_MAXSIZE);
        verify(stringSerializer).serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE);
    }

    @Override
    protected Serializer<RegisterClient, SuplaRegisterClient> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterClient> entityClass() {
        return RegisterClient.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new RegisterClientSerializerImpl(null);
    }
}