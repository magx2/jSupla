package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@Deprecated
@SuppressWarnings("WeakerAccess")
public class RegisterDeviceSerializerImplTest extends SerializerTest<RegisterDevice, SuplaRegisterDevice> {
    @InjectMocks RegisterDeviceSerializerImpl serializer;
    @Mock StringSerializer stringSerializer;
    @Mock DeviceChannelSerializer deviceChannelSerializer;

    @Override
    protected RegisterDevice given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void then(final RegisterDevice entity, final SuplaRegisterDevice proto) {
        assertThat(proto.locationId).isEqualTo(entity.getLocationId());
        verify(stringSerializer).serializePassword(entity.getLocationPassword(), SUPLA_LOCATION_PWD_MAXSIZE);
        verify(stringSerializer).serializeHexString(entity.getGuid());
        verify(stringSerializer).serialize(entity.getName(), SUPLA_DEVICE_NAME_MAXSIZE);
        verify(stringSerializer).serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE);
        assertThat((int) proto.channelCount).isEqualTo(entity.getChannels().size());
        entity.getChannels().getChannels().forEach(channel -> verify(deviceChannelSerializer).serialize(channel));

        verifyNoMoreInteractions(stringSerializer);
        verifyNoMoreInteractions(deviceChannelSerializer);
    }

    @Override
    protected Serializer<RegisterDevice, SuplaRegisterDevice> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterDevice> entityClass() {
        return RegisterDevice.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new RegisterDeviceSerializerImpl(null, deviceChannelSerializer);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenDeviceChannelSerializerIsNull() {
        new RegisterDeviceSerializerImpl(stringSerializer, null);
    }
}