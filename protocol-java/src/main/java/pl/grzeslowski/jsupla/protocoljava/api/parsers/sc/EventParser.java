package pl.grzeslowski.jsupla.protocoljava.api.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;

public interface EventParser extends ServerClientParser<Event, SuplaEvent> {
}
