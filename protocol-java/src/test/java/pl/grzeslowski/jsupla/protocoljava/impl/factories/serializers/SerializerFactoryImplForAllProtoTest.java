package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers;

import com.google.common.reflect.ClassPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.cs.ClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.dcs.DeviceClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.ds.DeviceServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sc.ServerClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sd.ServerDeviceSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sdc.ServerDeviceClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;

@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
@RunWith(Parameterized.class)
public class SerializerFactoryImplForAllProtoTest {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        return ClassPath.from(Proto.class.getClassLoader())
                       .getTopLevelClassesRecursive(Timeval.class.getPackage().getName())
                       .asList()
                       .stream()
                       .map(ClassPath.ClassInfo::load)
                       .filter(clazz -> !clazz.isInterface())
                       .filter(Entity.class::isAssignableFrom)
                       .map(clazz -> (Class<Entity>) clazz)
                       .map(clazz -> RANDOM_ENTITY.nextObject(clazz))
                       .map(entity -> new Object[]{entity})
                       .collect(Collectors.toList());
    }

    final Entity entity;

    @InjectMocks SerializerFactoryImpl factory;

    @Mock ClientServerSerializerFactory clientServerSerializerFactory;
    @Mock DeviceClientServerSerializerFactory deviceClientServerSerializerFactory;
    @Mock DeviceServerSerializerFactory deviceServerSerializerFactory;
    @Mock ServerClientSerializerFactory serverClientSerializerFactory;
    @Mock ServerDeviceSerializerFactory serverDeviceSerializerFactory;
    @Mock ServerDeviceClientSerializerFactory serverDeviceClientSerializerFactory;

    @Mock DeviceChannelSerializer deviceChannelSerializer;
    @Mock DeviceChannelBSerializer deviceChannelBSerializer;
    @Mock FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;
    @Mock ChannelValueSerializer channelValueSerializer;
    @Mock TimevalSerializer timevalSerializer;

    public SerializerFactoryImplForAllProtoTest(final Entity entity) {
        this.entity = entity;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldWorkForEntity() {
        factory.getSerializer(entity);
    }
}
