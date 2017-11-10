package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.dcs;

import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.SerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DeviceClientServerSerializerFactoryImplTest extends SerializerFactoryTest<DeviceClientServerEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {PingServer.class},
                {SetActivityTimeout.class}
        });
    }

    @InjectMocks DeviceClientServerSerializerFactoryImpl factory;

    @Mock PingServerSerializer pingServerSerializer;
    @Mock SetActivityTimeoutSerializer setActivityTimeoutSerializer;

    public DeviceClientServerSerializerFactoryImplTest(final Class<DeviceClientServerEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected SerializerFactory<? super DeviceClientServerEntity, ? extends Proto> getFactory() {
        return factory;
    }

    @Override
    protected Class<DeviceClientServerEntity> superInterface() {
        return DeviceClientServerEntity.class;
    }
}