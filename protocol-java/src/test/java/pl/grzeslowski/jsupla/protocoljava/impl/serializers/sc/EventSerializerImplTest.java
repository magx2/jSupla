package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SuppressWarnings("WeakerAccess")
public class EventSerializerImplTest extends SerializerTest<Event, SuplaEvent> {
    @InjectMocks
    EventSerializerImpl serializer;
    @Mock
    StringSerializer stringSerializer;

    @Override
    protected Event given() {
        givenStringSerializer(stringSerializer);
        return super.given();
    }

    @Override
    protected void then(final Event entity, final SuplaEvent proto) {
        assertThat(proto.event).isEqualTo(entity.getEvent());
        assertThat(proto.channelId).isEqualTo(entity.getChannelId());
        assertThat(proto.durationMs).isEqualTo(entity.getDurationMs());
        assertThat(proto.senderId).isEqualTo(entity.getSenderId());
        assertThat(proto.senderNameSize).isEqualTo(entity.getSenderName().length());
        verify(stringSerializer).serialize(entity.getSenderName());
    }

    @Override
    protected Serializer<Event, SuplaEvent> serializer() {
        return serializer;
    }

    @Override
    protected Class<Event> entityClass() {
        return Event.class;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenStringSerializerIsNull() {
        new EventSerializerImpl(null);
    }
}