package pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.AbstractSerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DeviceClientServerSerializerImplTest
    extends AbstractSerializerFactoryTest<DeviceClientServerEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {PingServer.class},
            {SetActivityTimeout.class}
        });
    }

    @InjectMocks
    DeviceClientServerSerializerImpl serializer;

    @Mock
    PingServerSerializer pingServerSerializer;
    @Mock
    SetActivityTimeoutSerializer setActivityTimeoutSerializer;

    public DeviceClientServerSerializerImplTest(final Class<DeviceClientServerEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected Serializer<DeviceClientServerEntity, ?> getSerializer() {
        return serializer;
    }

    @Override
    protected Class<DeviceClientServerEntity> superInterface() {
        return DeviceClientServerEntity.class;
    }
}