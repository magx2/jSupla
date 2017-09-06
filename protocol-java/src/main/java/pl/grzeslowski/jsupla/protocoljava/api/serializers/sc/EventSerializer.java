package pl.grzeslowski.jsupla.protocoljava.api.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;

public interface EventSerializer extends ServerClientEntitySerializer<Event, SuplaEvent> {
}
