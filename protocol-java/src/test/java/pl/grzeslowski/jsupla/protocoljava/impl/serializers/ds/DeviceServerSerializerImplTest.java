package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.*;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.AbstractSerializerFactoryTest;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(Parameterized.class)
public class DeviceServerSerializerImplTest extends AbstractSerializerFactoryTest<DeviceServerEntity> {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {ChannelNewValueResult.class},
            {RegisterDevice.class},
            {RegisterDeviceB.class},
            {RegisterDeviceC.class},
            {FirmwareUpdateParams.class},
            {DeviceChannelValue.class},
            {RegisterDeviceD.class}
        });
    }

    @InjectMocks
    DeviceServerSerializerImpl serializer;

    @Mock
    ChannelNewValueResultSerializer channelNewValueResultSerializer;
    @Mock
    RegisterDeviceSerializer registerDeviceSerializer;
    @Mock
    RegisterDeviceBSerializer registerDeviceBSerializer;
    @Mock
    RegisterDeviceCSerializer registerDeviceCSerializer;
    @Mock
    DeviceChannelValueSerializer deviceChannelValueSerializer;
    @Mock
    FirmwareUpdateParamsSerializer firmwareUpdateParamsSerializer;
    @Mock
    RegisterDeviceDSerializer registerDeviceDSerializer;

    public DeviceServerSerializerImplTest(final Class<DeviceServerEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected Serializer<DeviceServerEntity, ?> getSerializer() {
        return serializer;
    }

    @Override
    protected Class<DeviceServerEntity> superInterface() {
        return DeviceServerEntity.class;
    }
}