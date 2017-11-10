package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.sdc;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.GetVersionResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.PingServerResultClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.SetActivityTimeoutResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.VersionErrorSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.SerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class ServerDeviceClientSerializerFactoryImplTest extends SerializerFactoryTest<ServerDeviceClientEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {GetVersionResult.class},
                {VersionError.class},
                {SetActivityTimeoutResult.class},
                {PingServerResultClient.class}
        });
    }

    @InjectMocks ServerDeviceClientSerializerFactoryImpl factory;

    @Mock GetVersionResultSerializer getVersionResultSerializer;
    @Mock PingServerResultClientSerializer pingServerResultClientSerializer;
    @Mock SetActivityTimeoutResultSerializer setActivityTimeoutResultSerializer;
    @Mock VersionErrorSerializer versionErrorSerializer;

    public ServerDeviceClientSerializerFactoryImplTest(final Class<ServerDeviceClientEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected SerializerFactory<? super ServerDeviceClientEntity, ? extends Proto> getFactory() {
        return factory;
    }

    @Override
    protected Class<ServerDeviceClientEntity> superInterface() {
        return ServerDeviceClientEntity.class;
    }
}