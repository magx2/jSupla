package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class RegisterDeviceDSerializerImplTest extends SerializerTest<RegisterDeviceD, SuplaRegisterDeviceD> {
    @InjectMocks
    RegisterDeviceDSerializerImpl serializer;

    @Mock
    StringSerializer stringSerializer;
    @Mock
    DeviceChannelBSerializer deviceChannelBSerializer;

    @Before
    public void setUp() {
        givenStringSerializer(stringSerializer);
    }

    @Override
    protected void then(RegisterDeviceD entity, SuplaRegisterDeviceD proto) {
        verify(stringSerializer).serialize(entity.getEmail(), SUPLA_EMAIL_MAXSIZE);
        verify(stringSerializer).serialize(entity.getAuthKey(), SUPLA_AUTHKEY_SIZE);
        verify(stringSerializer).serializeHexString(entity.getGuid());
        verify(stringSerializer).serialize(entity.getName(), SUPLA_DEVICE_NAME_MAXSIZE);
        verify(stringSerializer).serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE);
        verify(stringSerializer).serialize(entity.getServerName(), SUPLA_SERVER_NAME_MAXSIZE);
        assertThat(proto.channelCount).isEqualTo((short) entity.getChannels().size());
        entity.getChannels().forEach(channel -> verify(deviceChannelBSerializer).serialize(channel));
    }

    @Override
    protected Serializer<RegisterDeviceD, SuplaRegisterDeviceD> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterDeviceD> entityClass() {
        return RegisterDeviceD.class;
    }
}