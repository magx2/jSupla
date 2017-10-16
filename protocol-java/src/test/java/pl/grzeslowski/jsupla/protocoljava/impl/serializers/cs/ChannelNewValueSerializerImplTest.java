package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
@SuppressWarnings("WeakerAccess")
public class ChannelNewValueSerializerImplTest extends SerializerTest<ChannelNewValue, SuplaChannelNewValue> {
    @InjectMocks ChannelNewValueSerializerImpl serializer;
    @Mock ChannelTypeEncoder channelTypeEncoder;

    @Override
    protected ChannelNewValue given() {
        final ChannelNewValue entity = super.given();
        BDDMockito.given(channelTypeEncoder.encode(entity.getValue())).willReturn(new byte[SUPLA_CHANNELVALUE_SIZE]);
        return entity;
    }

    @Override
    protected void then(final ChannelNewValue entity, final SuplaChannelNewValue proto) {
        assertThat(proto.channelId).isEqualTo((byte) entity.getChannelId());
        verify(channelTypeEncoder).encode(entity.getValue());
    }

    @Override
    protected Serializer<ChannelNewValue, SuplaChannelNewValue> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelNewValue> entityClass() {
        return ChannelNewValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeEncoderIsNull() {
        new ChannelNewValueSerializerImpl(null);
    }
}