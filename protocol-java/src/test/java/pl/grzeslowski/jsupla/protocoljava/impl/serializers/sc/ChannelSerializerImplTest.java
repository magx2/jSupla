package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
public class ChannelSerializerImplTest extends SerializerTest<Channel, SuplaChannel> {
    @InjectMocks
    ChannelSerializerImpl serializer;
    @Mock
    ChannelValueSerializer channelValueSerializer;
    @Mock
    StringSerializer stringSerializer;

    @Override
    protected Channel given() {
        final Channel entity = super.given();
        BDDMockito.given(channelValueSerializer.serialize(entity.getChannelValue()))
            .willReturn(RANDOM_SUPLA.nextObject(SuplaChannelValue.class));
        givenStringSerializer(stringSerializer);
        return entity;
    }

    @Override
    protected void then(final Channel entity, final SuplaChannel proto) {
        assertThat((int) proto.eol).isEqualTo(entity.getEol());
        assertThat(proto.id).isEqualTo(entity.getId());
        assertThat(proto.locationId).isEqualTo(entity.getLocationId());
        assertThat(proto.func).isEqualTo(entity.getFunction());
        assertThat(proto.online).isEqualTo((byte) (entity.isOnline() ? 1 : 0));
        assertThat(proto.captionSize).isEqualTo(entity.getCaption().length());
        verify(channelValueSerializer).serialize(entity.getChannelValue());
        verify(stringSerializer).serialize(entity.getCaption());
    }

    @Override
    protected Serializer<Channel, SuplaChannel> serializer() {
        return serializer;
    }

    @Override
    protected Class<Channel> entityClass() {
        return Channel.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueSerializerIsNull() {
        new ChannelSerializerImpl(null, stringSerializer);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new ChannelSerializerImpl(channelValueSerializer, null);
    }
}