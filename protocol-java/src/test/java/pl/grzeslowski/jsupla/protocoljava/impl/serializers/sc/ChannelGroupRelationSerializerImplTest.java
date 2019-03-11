package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;


import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("WeakerAccess")
public class ChannelGroupRelationSerializerImplTest extends SerializerTest<ChannelGroupRelation, SuplaChannelGroupRelation> {
    @InjectMocks
    ChannelGroupRelationSerializerImpl serializer;

    @Override
    protected void then(ChannelGroupRelation entity, SuplaChannelGroupRelation proto) {
        assertThat(proto.eol).isEqualTo((byte) entity.getEol());
        assertThat(proto.channelId).isEqualTo(entity.getChannelId());
        assertThat(proto.channelGroupId).isEqualTo(entity.getChannelGroupId());
    }

    @Override
    protected Serializer<ChannelGroupRelation, SuplaChannelGroupRelation> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelGroupRelation> entityClass() {
        return ChannelGroupRelation.class;
    }
}