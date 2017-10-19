package pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.PingServerParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class PingServerParserImpl implements PingServerParser {
    private final TimevalParser timevalParser;

    public PingServerParserImpl(final TimevalParser timevalParser) {
        this.timevalParser = requireNonNull(timevalParser);
    }

    @Override
    public PingServer parse(@NotNull final SuplaPingServer proto) {
        return new PingServer(timevalParser.parse(proto.timeval));
    }
}
