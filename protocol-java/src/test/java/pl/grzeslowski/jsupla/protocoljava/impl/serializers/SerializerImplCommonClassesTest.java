package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ClientServerEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceServerEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ServerClientEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ServerDeviceEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.ServerDeviceClientEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocoljava.common.RandomEntity.RANDOM_ENTITY;
import static pl.grzeslowski.jsupla.protocoljava.common.TestUtil.getDeclaredField;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class SerializerImplCommonClassesTest {

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {DeviceChannel.class, "deviceChannelSerializer"},
                {DeviceChannelB.class, "deviceChannelBSerializer"},
                {FirmwareUpdateUrl.class, "firmwareUpdateUrlSerializer"},
                {ChannelValue.class, "channelValueSerializer"},
                {Timeval.class, "timevalSerializer"}
        });
    }

    @InjectMocks SerializerImpl factory;

    @Mock ClientServerEntitySerializer clientServerSerializer;
    @Mock DeviceClientServerEntitySerializer deviceClientServerSerializer;
    @Mock DeviceServerEntitySerializer deviceServerSerializer;
    @Mock ServerClientEntitySerializer serverClientSerializer;
    @Mock ServerDeviceEntitySerializer serverDeviceSerializer;
    @Mock ServerDeviceClientEntitySerializer serverDeviceClientSerializer;

    @Mock DeviceChannelSerializer deviceChannelSerializer;
    @Mock DeviceChannelBSerializer deviceChannelBSerializer;
    @Mock FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;
    @Mock ChannelValueSerializer channelValueSerializer;
    @Mock TimevalSerializer timevalSerializer;

    final Class<? extends Entity> clazzToTest;
    final String fieldName;

    public SerializerImplCommonClassesTest(Class<? extends Entity> clazzToTest, String fieldName) {
        this.clazzToTest = clazzToTest;
        this.fieldName = fieldName;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldFindSerializerForCommonClasses() throws Exception {

        // given
        final Entity entity = RANDOM_ENTITY.nextObject(clazzToTest);
        final Serializer<? super Entity, ?> expectedSerializer = (Serializer<? super Entity, ?>) getDeclaredField(
                this.getClass(), fieldName).get(this);

        // when
        factory.serialize(entity);

        // then
        verify(expectedSerializer).serialize(entity);
    }
}
