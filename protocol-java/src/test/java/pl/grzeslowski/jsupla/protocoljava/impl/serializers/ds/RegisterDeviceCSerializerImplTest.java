package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@SuppressWarnings("WeakerAccess")
public class RegisterDeviceCSerializerImplTest extends SerializerTest<RegisterDeviceC, SuplaRegisterDeviceC> {
    @InjectMocks RegisterDeviceCSerializerImpl serializer;
    @Mock StringSerializer stringSerializer;
    @Mock DeviceChannelBSerializer deviceChannelBSerializer;

    @Override
    protected RegisterDeviceC given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @Override
    protected void then(final RegisterDeviceC entity, final SuplaRegisterDeviceC proto) {
        assertThat(proto.locationId).isEqualTo(entity.getLocationId());
        verify(stringSerializer).serializePassword(entity.getLocationPassword(), SUPLA_LOCATION_PWD_MAXSIZE);
        verify(stringSerializer).serializeHexString(entity.getGuid());
        verify(stringSerializer).serialize(entity.getName(), SUPLA_DEVICE_NAME_MAXSIZE);
        verify(stringSerializer).serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE);
        assertThat((int) proto.channelCount).isEqualTo(entity.getChannelCount());
        entity.getChannels().getChannels().forEach(channel ->
                                                           verify(deviceChannelBSerializer).serialize(channel));
        verify(stringSerializer).serialize(entity.getServerName(), SUPLA_SERVER_NAME_MAXSIZE);

        verifyNoMoreInteractions(stringSerializer);
        verifyNoMoreInteractions(deviceChannelBSerializer);
    }

    @Override
    protected Serializer<RegisterDeviceC, SuplaRegisterDeviceC> serializer() {
        return serializer;
    }

    @Override
    protected Class<RegisterDeviceC> entityClass() {
        return RegisterDeviceC.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new RegisterDeviceCSerializerImpl(null, deviceChannelBSerializer);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenDeviceChannelBSerializerIsNull() {
        new RegisterDeviceCSerializerImpl(stringSerializer, null);
    }
}