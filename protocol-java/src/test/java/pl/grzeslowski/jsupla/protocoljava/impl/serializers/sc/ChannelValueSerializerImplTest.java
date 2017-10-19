package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
public class ChannelValueSerializerImplTest extends SerializerTest<ChannelValue, SuplaChannelValue> {
    @InjectMocks ChannelValueSerializerImpl serializer;
    @Mock ChannelValueSerializer channelValueSerializer;

    @Override
    protected ChannelValue given() {
        final ChannelValue entity = super.given();
        BDDMockito.given(channelValueSerializer.serialize(entity.getValue()))
                .willReturn(
                        RANDOM_SUPLA.nextObject(pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class));
        return entity;
    }

    @Override
    protected void then(final ChannelValue entity, final SuplaChannelValue proto) {
        assertThat((int) proto.eol).isEqualTo(entity.getEol());
        assertThat(proto.id).isEqualTo(entity.getId());
        assertThat(proto.online).isEqualTo((byte) (entity.isOnline() ? 1 : 0));
        verify(channelValueSerializer).serialize(entity.getValue());
    }

    @Override
    protected Serializer<ChannelValue, SuplaChannelValue> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelValue> entityClass() {
        return ChannelValue.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueSerializerIsNull() {
        new ChannelValueSerializerImpl(null);
    }
}