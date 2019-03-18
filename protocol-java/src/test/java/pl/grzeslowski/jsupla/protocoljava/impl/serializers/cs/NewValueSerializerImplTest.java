package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import org.junit.Before;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.NewValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;


public class NewValueSerializerImplTest extends SerializerTest<NewValue, SuplaNewValue> {
    @InjectMocks
    NewValueSerializerImpl serializer;
    @Mock
    ChannelTypeEncoder channelTypeEncoder;

    @Before
    public void setUp() {
        BDDMockito.given(channelTypeEncoder.encode(any())).willReturn(new byte[SUPLA_CHANNELVALUE_SIZE]);
    }

    @Override
    protected void then(NewValue entity, SuplaNewValue proto) {
        assertThat(proto.id).isEqualTo(entity.getId());
        assertThat(proto.target).isEqualTo((byte) entity.getTarget());
        verify(channelTypeEncoder).encode(entity.getValue());
    }

    @Override
    protected Serializer<NewValue, SuplaNewValue> serializer() {
        return serializer;
    }

    @Override
    protected Class<NewValue> entityClass() {
        return NewValue.class;
    }
}