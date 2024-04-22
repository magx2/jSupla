package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueBSerializerImplTest extends SerializerTest<ChannelNewValueB, SuplaChannelNewValueB> {
    @InjectMocks
    ChannelNewValueBSerializerImpl serializer;
    @Mock
    ChannelTypeEncoder channelTypeEncoder;

    @Override
    protected ChannelNewValueB given() {
        final ChannelNewValueB entity = super.given();
        BDDMockito.given(channelTypeEncoder.encode(entity.getValue())).willReturn(new byte[SUPLA_CHANNELVALUE_SIZE]);
        return entity;
    }

    @Override
    protected void then(final ChannelNewValueB entity, final SuplaChannelNewValueB proto) {
        assertThat(proto.channelId).isEqualTo(entity.getChannelId());
        verify(channelTypeEncoder).encode(entity.getValue());
    }

    @Override
    protected Serializer<ChannelNewValueB, SuplaChannelNewValueB> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelNewValueB> entityClass() {
        return ChannelNewValueB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelTypeEncoderIsNull() {
        new ChannelNewValueBSerializerImpl(null);
    }
}