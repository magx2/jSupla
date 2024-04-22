package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
public class DeviceChannelSerializerImplTest extends SerializerTest<DeviceChannel, SuplaDeviceChannel> {
    @InjectMocks
    DeviceChannelSerializerImpl serializer;
    @Mock
    ChannelTypeEncoder channelTypeEncoder;

    @Override
    protected DeviceChannel given() {
        final DeviceChannel entity = super.given();
        BDDMockito.given(channelTypeEncoder.encode(entity.getValue()))
            .willReturn(new byte[SUPLA_CHANNELVALUE_SIZE]);
        return entity;
    }

    @Override
    protected void then(final DeviceChannel entity, final SuplaDeviceChannel proto) {
        assertThat((int) proto.number).isEqualTo(entity.getNumber());
        assertThat(proto.type).isEqualTo(entity.getType());
        verify(channelTypeEncoder).encode(entity.getValue());
    }

    @Override
    protected Serializer<DeviceChannel, SuplaDeviceChannel> serializer() {
        return serializer;
    }

    @Override
    protected Class<DeviceChannel> entityClass() {
        return DeviceChannel.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeEncoderIsNull() {
        new DeviceChannelSerializerImpl(null);
    }
}