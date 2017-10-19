package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
public class ChannelPackSerializerImplTest extends SerializerTest<ChannelPack, SuplaChannelPack> {
    @InjectMocks ChannelPackSerializerImpl serializer;
    @Mock ChannelSerializer channelSerializer;

    @Override
    protected ChannelPack given() {
        BDDMockito.given(channelSerializer.serialize(any())).willReturn(RANDOM_SUPLA.nextObject(SuplaChannel.class));
        return super.given();
    }

    @Override
    protected void then(final ChannelPack entity, final SuplaChannelPack proto) {
        assertThat(proto.count).isEqualTo(entity.getChannels().size());
        assertThat(proto.totalLeft).isEqualTo(entity.getTotalLeft());
        entity.getChannels().forEach(channel -> verify(channelSerializer).serialize(channel));
        verifyNoMoreInteractions(channelSerializer);
    }

    @Override
    protected Serializer<ChannelPack, SuplaChannelPack> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelPack> entityClass() {
        return ChannelPack.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelSerializerIsNull() {
        new ChannelPackSerializerImpl(null);
    }
}