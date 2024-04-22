package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelBSerializerImplTest extends SerializerTest<DeviceChannelB, SuplaDeviceChannelB> {
    @InjectMocks
    DeviceChannelBSerializerImpl serializer;
    @Mock
    ChannelTypeEncoder channelTypeEncoder;

    @Override
    protected DeviceChannelB given() {
        final DeviceChannelB entity = super.given();
        BDDMockito.given(channelTypeEncoder.encode(entity.getValue()))
            .willReturn(new byte[SUPLA_CHANNELVALUE_SIZE]);
        return entity;
    }

    @Override
    protected void then(final DeviceChannelB entity, final SuplaDeviceChannelB proto) {
        assertThat((int) proto.number).isEqualTo(entity.getNumber());
        assertThat(proto.type).isEqualTo(entity.getType());
        verify(channelTypeEncoder).encode(entity.getValue());
        assertThat(proto.funcList).isEqualTo(entity.getFunction());
        assertThat(proto.defaultValue).isEqualTo(entity.getDefaultValue());
    }

    @Override
    protected Serializer<DeviceChannelB, SuplaDeviceChannelB> serializer() {
        return serializer;
    }

    @Override
    protected Class<DeviceChannelB> entityClass() {
        return DeviceChannelB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeEncoderIsNull() {
        new DeviceChannelBSerializerImpl(null);
    }
}