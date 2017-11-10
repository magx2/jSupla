package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.SetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.cs.ClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.dcs.DeviceClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.ds.DeviceServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sc.ServerClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sd.ServerDeviceSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sdc.ServerDeviceClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;
import static pl.grzeslowski.jsupla.protocoljava.common.TestUtil.getDeclaredField;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class SerializerFactoryImplTest {
    static final Collection<Class<? extends ClientServerEntity>> CLIENT_SERVER_CLASSES = Arrays.asList(
            ChannelNewValue.class,
            ChannelNewValueB.class,
            RegisterClient.class,
            RegisterClientB.class
    );
    static final Collection<Class<? extends DeviceClientServerEntity>> DEVICE_CLIENT_SERVER_CLASSES = Arrays.asList(
            PingServer.class,
            SetActivityTimeout.class
    );
    static final Collection<Class<? extends DeviceServerEntity>> DEVICE_SERVER_CLASSES = Arrays.asList(
            ChannelNewValueResult.class,
            DeviceChannelValue.class,
            FirmwareUpdateParams.class,
            RegisterDevice.class,
            RegisterDeviceB.class,
            RegisterDeviceC.class
    );
    static final Collection<Class<? extends ServerClientEntity>> SERVER_CLIENT_CLASSES = Arrays.asList(
            Channel.class,
            ChannelPack.class,
            pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue.class,
            Event.class,
            Location.class,
            LocationPack.class,
            RegisterClientResult.class
    );
    static final Collection<Class<? extends ServerDeviceEntity>> SERVER_DEVICE_CLASSES = Arrays.asList(
            pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue.class,
            FirmwareUpdateUrlResult.class,
            RegisterDeviceResult.class
    );
    static final Collection<Class<? extends ServerDeviceClientEntity>> SERVER_DEVICE_CLIENT_CLASSES = Arrays.asList(
            GetVersionResult.class,
            PingServerResultClient.class,
            SetActivityTimeoutResult.class,
            VersionError.class
    );

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() throws NoSuchFieldException {
        return Arrays.asList(new Object[][]{
                {CLIENT_SERVER_CLASSES, "clientServerSerializerFactory"},
                {DEVICE_CLIENT_SERVER_CLASSES, "deviceClientServerSerializerFactory"},
                {DEVICE_SERVER_CLASSES, "deviceServerSerializerFactory"},
                {SERVER_CLIENT_CLASSES, "serverClientSerializerFactory"},
                {SERVER_DEVICE_CLASSES, "serverDeviceSerializerFactory"},
                {SERVER_DEVICE_CLIENT_CLASSES, "serverDeviceClientSerializerFactory"},
        });
    }

    final Collection<Class<Entity>> entitiesToTestClass;
    final String resultFieldName;
    SerializerFactory<? super Entity, ?> resultField;

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

    public SerializerFactoryImplTest(final Collection<Class<Entity>> entitiesToTestClass,
                                     final String resultFieldName) throws Exception {
        this.entitiesToTestClass = entitiesToTestClass;
        this.resultFieldName = resultFieldName;
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        resultField = (SerializerFactory<? super Entity, ?>) getDeclaredField(SerializerFactoryImplTest.class,
                resultFieldName).get(this);
    }

    @Test
    public void shouldFindSerializer() throws Exception {
        entitiesToTestClass
                .stream()
                .map(entityToTest -> RANDOM_ENTITY.nextObject(entityToTest))
                .forEach(this::shouldFindSerializerForEntity);
    }

    private void shouldFindSerializerForEntity(Entity entityToTest) {
        final Serializer serializer = factory.getSerializer(entityToTest);
        verify(resultField).getSerializer(entityToTest);
    }
}