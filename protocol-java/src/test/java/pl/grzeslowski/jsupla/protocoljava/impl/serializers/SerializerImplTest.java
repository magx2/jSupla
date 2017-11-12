package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

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
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ServerClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ServerDeviceSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.ServerDeviceClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;
import static pl.grzeslowski.jsupla.protocoljava.common.TestUtil.getDeclaredField;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class SerializerImplTest {
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
                {CLIENT_SERVER_CLASSES, "clientServerSerializer"},
                {DEVICE_CLIENT_SERVER_CLASSES, "deviceClientServerSerializer"},
                {DEVICE_SERVER_CLASSES, "deviceServerSerializer"},
                {SERVER_CLIENT_CLASSES, "serverClientSerializer"},
                {SERVER_DEVICE_CLASSES, "serverDeviceSerializer"},
                {SERVER_DEVICE_CLIENT_CLASSES, "serverDeviceClientSerializer"},
        });
    }

    final Collection<Class<Entity>> entitiesToTestClass;
    final String resultFieldName;
    Serializer<? super Entity, ?> resultField;

    @InjectMocks SerializerImpl factory;

    @Mock ClientServerSerializer clientServerSerializer;
    @Mock DeviceClientServerSerializer deviceClientServerSerializer;
    @Mock DeviceServerSerializer deviceServerSerializer;
    @Mock ServerClientSerializer serverClientSerializer;
    @Mock ServerDeviceSerializer serverDeviceSerializer;
    @Mock ServerDeviceClientSerializer serverDeviceClientSerializer;

    @Mock DeviceChannelSerializer deviceChannelSerializer;
    @Mock DeviceChannelBSerializer deviceChannelBSerializer;
    @Mock FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;
    @Mock ChannelValueSerializer channelValueSerializer;
    @Mock TimevalSerializer timevalSerializer;

    public SerializerImplTest(final Collection<Class<Entity>> entitiesToTestClass,
                              final String resultFieldName) throws Exception {
        this.entitiesToTestClass = entitiesToTestClass;
        this.resultFieldName = resultFieldName;
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        resultField = (Serializer<? super Entity, ?>) getDeclaredField(SerializerImplTest.class,
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
        factory.serialize(entityToTest);
        verify(resultField).serialize(entityToTest);
    }
}