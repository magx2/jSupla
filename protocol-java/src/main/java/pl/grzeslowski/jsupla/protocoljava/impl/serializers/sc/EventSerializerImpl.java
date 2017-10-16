package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.EventSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class EventSerializerImpl implements EventSerializer {
    private final StringSerializer stringSerializer;

    public EventSerializerImpl(final StringSerializer stringSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaEvent serialize(@NotNull final Event entity) {
        return new SuplaEvent(
                                     entity.getEvent(),
                                     entity.getChannelId(),
                                     entity.getDurationMs(),
                                     entity.getSenderId(),
                                     entity.getSenderName().length(),
                                     stringSerializer.serialize(entity.getSenderName())
        );
    }
}
