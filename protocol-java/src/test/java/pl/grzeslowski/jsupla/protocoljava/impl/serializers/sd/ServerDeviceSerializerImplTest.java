package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ChannelNewValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.RegisterDeviceResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.AbstractSerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class ServerDeviceSerializerImplTest extends AbstractSerializerFactoryTest<ServerDeviceEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ChannelNewValue.class},
                {FirmwareUpdateUrlResult.class},
                {RegisterDeviceResult.class}
        });
    }

    @InjectMocks ServerDeviceSerializerImpl serializer;

    @Mock ChannelNewValueSerializer channelNewValueSerializer;
    @Mock FirmwareUpdateUrlResultSerializer firmwareUpdateUrlResultSerializer;
    @Mock RegisterDeviceResultSerializer registerDeviceResultSerializer;

    public ServerDeviceSerializerImplTest(final Class<ServerDeviceEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected Serializer<ServerDeviceEntity, ?> getSerializer() {
        return serializer;
    }

    @Override
    protected Class<ServerDeviceEntity> superInterface() {
        return ServerDeviceEntity.class;
    }
}