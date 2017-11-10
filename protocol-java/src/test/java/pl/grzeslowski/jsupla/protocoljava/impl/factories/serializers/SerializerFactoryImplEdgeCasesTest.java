package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(MockitoJUnitRunner.class)
public class SerializerFactoryImplEdgeCasesTest {
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenEntityIsUnknownIsNull() {
        factory.getSerializer(new Entity() {
        });
    }
}