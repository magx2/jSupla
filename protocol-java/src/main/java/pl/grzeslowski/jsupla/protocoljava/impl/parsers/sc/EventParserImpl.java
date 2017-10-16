package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.EventParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class EventParserImpl implements EventParser {
    private final StringParser stringParser;

    public EventParserImpl(final StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public Event parse(@NotNull final SuplaEvent proto) {
        return new Event(
                                proto.event,
                                proto.channelId,
                                proto.durationMs,
                                proto.senderId,
                                stringParser.parse(proto.senderName)
        );
    }
}
