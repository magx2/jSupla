package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


public class ChannelPackBSerializerImplTest extends SerializerTest<ChannelPackB, SuplaChannelPackB> {
    @InjectMocks
    ChannelPackBSerializerImpl serializer;

    @Mock
    ChannelBSerializer channelBSerializer;

    @Override
    protected void then(ChannelPackB entity, SuplaChannelPackB proto) {
        assertThat(proto.count).isEqualTo(entity.getChannels().size());
        assertThat(proto.totalLeft).isEqualTo(entity.getTotalLeft());
        entity.getChannels()
                .forEach(channel -> verify(channelBSerializer).serialize(channel));
    }

    @Override
    protected Serializer<ChannelPackB, SuplaChannelPackB> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelPackB> entityClass() {
        return ChannelPackB.class;
    }
}