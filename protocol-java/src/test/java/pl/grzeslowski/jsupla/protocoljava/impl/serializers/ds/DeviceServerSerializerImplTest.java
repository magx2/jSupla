package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.ChannelNewValueResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.FirmwareUpdateParamsSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceCSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceSerializer;
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
        });
    }

    @InjectMocks DeviceServerSerializerImpl serializer;

    @Mock ChannelNewValueResultSerializer channelNewValueResultSerializer;
    @Mock RegisterDeviceSerializer registerDeviceSerializer;
    @Mock RegisterDeviceBSerializer registerDeviceBSerializer;
    @Mock RegisterDeviceCSerializer registerDeviceCSerializer;
    @Mock DeviceChannelValueSerializer deviceChannelValueSerializer;
    @Mock FirmwareUpdateParamsSerializer firmwareUpdateParamsSerializer;

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