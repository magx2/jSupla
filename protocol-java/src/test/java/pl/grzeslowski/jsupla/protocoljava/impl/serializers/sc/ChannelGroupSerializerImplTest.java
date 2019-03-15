package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ChannelGroupSerializerImplTest extends SerializerTest<ChannelGroup, SuplaChannelGroup> {
    @InjectMocks
    ChannelGroupSerializerImpl serializer;
    @Mock
    StringSerializer stringSerializer;

    @Before
    public void setUp() {
        givenStringSerializer(stringSerializer);
    }

    @Override
    protected void then(ChannelGroup entity, SuplaChannelGroup proto) {
        assertThat(proto.eol).isEqualTo((byte) entity.getEol());
        assertThat(proto.id).isEqualTo(entity.getId());
        assertThat(proto.locationId).isEqualTo(entity.getLocationId());
        assertThat(proto.func).isEqualTo(entity.getFunction());
        assertThat(proto.altIcon).isEqualTo(entity.getAltIcon());
        assertThat(proto.flags).isEqualTo(entity.getFlags());
        assertThat(proto.captionSize).isEqualTo(proto.caption.length);
        verify(stringSerializer).serialize(entity.getCaption());
    }

    @Override
    protected Serializer<ChannelGroup, SuplaChannelGroup> serializer() {
        return serializer;
    }

    @Override
    protected Class<ChannelGroup> entityClass() {
        return ChannelGroup.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringParserIsNull() {
        new ChannelGroupSerializerImpl(null);
    }
}
