package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
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

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(MockitoJUnitRunner.class)
public class SerializerImplEdgeCasesTest {
    @InjectMocks
    SerializerImpl factory;

    @Mock
    ClientServerSerializer clientServerSerializer;
    @Mock
    DeviceClientServerSerializer deviceClientServerSerializer;
    @Mock
    DeviceServerSerializer deviceServerSerializer;
    @Mock
    ServerClientSerializer serverClientSerializer;
    @Mock
    ServerDeviceSerializer serverDeviceSerializer;
    @Mock
    ServerDeviceClientSerializer serverDeviceClientSerializer;

    @Mock
    DeviceChannelSerializer deviceChannelSerializer;
    @Mock
    DeviceChannelBSerializer deviceChannelBSerializer;
    @Mock
    FirmwareUpdateUrlSerializer firmwareUpdateUrlSerializer;
    @Mock
    ChannelValueSerializer channelValueSerializer;
    @Mock
    TimevalSerializer timevalSerializer;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenEntityIsUnknownIsNull() {
        factory.serialize(new Entity() {
        });
    }
}