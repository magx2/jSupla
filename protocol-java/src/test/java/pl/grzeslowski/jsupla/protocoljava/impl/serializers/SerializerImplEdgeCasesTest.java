package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
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

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(MockitoJUnitRunner.class)
public class SerializerImplEdgeCasesTest {
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenEntityIsUnknownIsNull() {
        factory.serialize(new Entity() {
        });
    }
}