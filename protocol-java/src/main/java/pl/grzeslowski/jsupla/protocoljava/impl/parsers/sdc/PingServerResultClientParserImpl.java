package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.PingServerResultClientParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class PingServerResultClientParserImpl implements PingServerResultClientParser {
    private final TimevalParser timevalParser;

    public PingServerResultClientParserImpl(final TimevalParser timevalParser) {
        this.timevalParser = requireNonNull(timevalParser);
    }

    @Override
    public PingServerResultClient parse(@NotNull final SuplaPingServerResultClient proto) {
        return new PingServerResultClient(timevalParser.parse(proto.timeval));
    }
}
