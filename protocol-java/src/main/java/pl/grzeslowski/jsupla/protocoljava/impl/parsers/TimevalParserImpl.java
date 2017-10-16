package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;

import javax.validation.constraints.NotNull;

public class TimevalParserImpl implements TimevalParser {
    @Override
    public Timeval parse(@NotNull final SuplaTimeval proto) {
        return new Timeval(proto.seconds, proto.milliseconds);
    }
}
