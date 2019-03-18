package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;


import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

public class ChannelValuePackSerializerImplTest extends SerializerTest<ChannelValuePack, SuplaChannelValuePack> {
    @InjectMocks
    ChannelValuePackSerializerImpl serializer;
    @Mock
    ChannelValueSerializer channelValueSerializer;

    @Override
    protected ChannelValuePack given() {
        BDDMockito.given(channelValueSerializer.serialize(any()))
                .willReturn(RANDOM_SUPLA.nextObject(SuplaChannelValue.class));
        return super.given();
    }

    @Override
    protected void then(ChannelValuePack entity, SuplaChannelValuePack proto) {
        assertThat(proto.count).isEqualTo(entity.getItems().size());
        assertThat(proto.totalLeft).isEqualTo(entity.getTotalLeft());
        entity.getItems().forEach(item -> verify(channelValueSerializer).serialize(item));
    }

    @Override
    protected Serializer<ChannelValuePack, SuplaChannelValuePack> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelValuePack> entityClass() {
        return ChannelValuePack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueSerializerIsNull() {
        new ChannelValuePackSerializerImpl(null);
    }
}
