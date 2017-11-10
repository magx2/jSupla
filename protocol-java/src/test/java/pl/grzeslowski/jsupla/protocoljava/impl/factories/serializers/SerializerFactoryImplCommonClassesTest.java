package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers;

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
import pl.grzeslowski.jsupla.protocoljava.common.RandomEntity;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.common.TestUtil.getDeclaredField;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class SerializerFactoryImplCommonClassesTest {

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

    final Class<? extends Entity> clazzToTest;
    final String fieldName;

    public SerializerFactoryImplCommonClassesTest(Class<? extends Entity> clazzToTest, String fieldName) {
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
        final Serializer<? super Entity, ?> expectedSerializer = (Serializer<? super Entity, ?>) getDeclaredField(
                this.getClass(), fieldName).get(this);

        // when
        final Serializer serializer = factory.getSerializer(RandomEntity.RANDOM_ENTITY.nextObject(clazzToTest));

        // then
        assertThat(serializer).isEqualTo(expectedSerializer);
    }
}
