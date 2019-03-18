package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupRelationSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

public class ChannelGroupRelationPackSerializerImplTest
    extends SerializerTest<ChannelGroupRelationPack, SuplaChannelGroupRelationPack> {
    @InjectMocks
    ChannelGroupRelationPackSerializerImpl serializer;
    @Mock
    ChannelGroupRelationSerializer channelGroupRelationSerializer;

    @Override
    protected ChannelGroupRelationPack given() {
        ChannelGroupRelationPack given = super.given();
        BDDMockito.given(channelGroupRelationSerializer.serialize(any()))
                .willReturn(RANDOM_SUPLA.nextObject(SuplaChannelGroupRelation.class));
        return given;
    }

    @Override
    protected void then(ChannelGroupRelationPack entity, SuplaChannelGroupRelationPack proto) {
        assertThat(proto.totalLeft).isEqualTo(entity.getTotalLeft());
        assertThat(proto.count).isEqualTo(entity.getItems().size());
        entity.getItems().forEach(item -> BDDMockito.verify(channelGroupRelationSerializer).serialize(item));
        verifyNoMoreInteractions(channelGroupRelationSerializer);
    }

    @Override
    protected Serializer<ChannelGroupRelationPack, SuplaChannelGroupRelationPack> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelGroupRelationPack> entityClass() {
        return ChannelGroupRelationPack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelGroupRelationSerializerIsNull() {
        new ChannelGroupRelationPackSerializerImpl(null);
    }
}