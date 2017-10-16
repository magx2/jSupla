package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelValueSerializerImplTest extends SerializerTest<DeviceChannelValue, SuplaDeviceChannelValue> {
    @InjectMocks DeviceChannelValueSerializerImpl serializer;
    @Mock ChannelTypeEncoder channelTypeEncoder;

    @Override
    protected DeviceChannelValue given() {
        final DeviceChannelValue entity = super.given();
        BDDMockito.given(channelTypeEncoder.encode(entity.getValue()))
                .willReturn(new byte[SUPLA_CHANNELVALUE_SIZE]);
        return entity;
    }

    @Override
    protected void then(final DeviceChannelValue entity, final SuplaDeviceChannelValue proto) {
        assertThat((int) proto.channelNumber).isEqualTo(entity.getChannelNumber());
        verify(channelTypeEncoder).encode(entity.getValue());
    }

    @Override
    protected Serializer<DeviceChannelValue, SuplaDeviceChannelValue> serializer() {
        return serializer;
    }

    @Override
    protected Class<DeviceChannelValue> entityClass() {
        return DeviceChannelValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeEncoderIsNull() {
        new DeviceChannelValueSerializerImpl(null);
    }
}