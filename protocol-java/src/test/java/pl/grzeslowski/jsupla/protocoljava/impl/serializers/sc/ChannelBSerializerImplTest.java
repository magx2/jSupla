package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;


import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

public class ChannelBSerializerImplTest extends SerializerTest<ChannelB, SuplaChannelB> {
    @InjectMocks
    ChannelBSerializerImpl serializer;
    @Mock
    ChannelValueSerializer channelValueSerializer;
    @Mock
    StringSerializer stringSerializer;

    @Override
    protected ChannelB given() {
        final ChannelB entity = super.given();
        BDDMockito.given(channelValueSerializer.serialize(entity.getChannelValue()))
                .willReturn(RANDOM_SUPLA.nextObject(SuplaChannelValue.class));
        givenStringSerializer(stringSerializer);
        return entity;
    }

    @Override
    protected void then(ChannelB entity, SuplaChannelB proto) {
        assertThat((int) proto.eol).isEqualTo(entity.getEol());
        assertThat(proto.id).isEqualTo(entity.getId());
        assertThat(proto.locationId).isEqualTo(entity.getLocationId());
        assertThat(proto.func).isEqualTo(entity.getFunction());
        assertThat(proto.online).isEqualTo((byte) (entity.isOnline() ? 1 : 0));
        assertThat(proto.captionSize).isEqualTo(entity.getCaption().length());
        verify(channelValueSerializer).serialize(entity.getChannelValue());
        verify(stringSerializer).serialize(entity.getCaption());

        assertThat(proto.altIcon).isEqualTo(entity.getAltIcon());
        assertThat(proto.flags).isEqualTo(entity.getFlags());
        assertThat(proto.protocolVersion).isEqualTo((short) entity.getProtocolVersion());
    }

    @Override
    protected Serializer<ChannelB, SuplaChannelB> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelB> entityClass() {
        return ChannelB.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenChannelValueSerializerIsNull() {
        new ChannelBSerializerImpl(null, stringSerializer);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new ChannelBSerializerImpl(channelValueSerializer, null);
    }
}