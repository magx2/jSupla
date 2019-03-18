package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;


public class RegisterClientCSerializerImplTest extends SerializerTest<RegisterClientC, SuplaRegisterClientC> {
    @InjectMocks
    RegisterClientCSerializerImpl serializer;

    @Mock
    StringSerializer stringSerializer;

    @Before
    public void setUp() {
        givenStringSerializer(stringSerializer);
    }

    @Override
    protected void then(RegisterClientC entity, SuplaRegisterClientC proto) {
        verify(stringSerializer).serialize(entity.getEmail(), SUPLA_EMAIL_MAXSIZE);
        verify(stringSerializer).serialize(entity.getAuthKey(), SUPLA_AUTHKEY_SIZE);
        verify(stringSerializer).serialize(entity.getGuid(), SUPLA_GUID_SIZE);
        verify(stringSerializer).serialize(entity.getName(), SUPLA_CLIENT_NAME_MAXSIZE);
        verify(stringSerializer).serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE);
        verify(stringSerializer).serialize(entity.getServerName(), SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    protected Serializer<RegisterClientC, SuplaRegisterClientC> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterClientC> entityClass() {
        return RegisterClientC.class;
    }
}