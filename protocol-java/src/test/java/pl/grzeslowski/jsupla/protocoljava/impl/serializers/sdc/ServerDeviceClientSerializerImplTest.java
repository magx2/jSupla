package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.GetVersionResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.PingServerResultClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.SetActivityTimeoutResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.VersionErrorSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.AbstractSerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class ServerDeviceClientSerializerImplTest extends AbstractSerializerFactoryTest<ServerDeviceClientEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {GetVersionResult.class},
                {VersionError.class},
                {SetActivityTimeoutResult.class},
                {PingServerResultClient.class}
        });
    }

    @InjectMocks ServerDeviceClientSerializerImpl serializer;

    @Mock GetVersionResultSerializer getVersionResultSerializer;
    @Mock PingServerResultClientSerializer pingServerResultClientSerializer;
    @Mock SetActivityTimeoutResultSerializer setActivityTimeoutResultSerializer;
    @Mock VersionErrorSerializer versionErrorSerializer;

    public ServerDeviceClientSerializerImplTest(final Class<ServerDeviceClientEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected Serializer<ServerDeviceClientEntity, ?> getSerializer() {
        return serializer;
    }

    @Override
    protected Class<ServerDeviceClientEntity> superInterface() {
        return ServerDeviceClientEntity.class;
    }
}