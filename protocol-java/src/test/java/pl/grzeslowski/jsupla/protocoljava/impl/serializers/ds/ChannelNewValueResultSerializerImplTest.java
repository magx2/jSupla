package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import org.mockito.InjectMocks;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("WeakerAccess")
public class ChannelNewValueResultSerializerImplTest extends SerializerTest<ChannelNewValueResult, SuplaChannelNewValueResult> {
    @InjectMocks ChannelNewValueResultSerializerImpl serializer;

    @Override
    protected void then(final ChannelNewValueResult entity, final SuplaChannelNewValueResult proto) {
        assertThat((int) proto.channelNumber).isEqualTo(entity.getChannelNumber());
        assertThat(proto.senderId).isEqualTo(entity.getSenderId());
        assertThat(proto.success).isEqualTo((byte) (entity.isSuccess() ? 1 : 0));
    }

    @Override
    protected Serializer<ChannelNewValueResult, SuplaChannelNewValueResult> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelNewValueResult> entityClass() {
        return ChannelNewValueResult.class;
    }
}