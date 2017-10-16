package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class LocationParserImpl implements LocationParser {
    private final StringParser stringParser;

    public LocationParserImpl(final StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public Location parse(@NotNull final SuplaLocation proto) {
        return new Location(proto.eol, proto.id, stringParser.parse(proto.caption));
    }
}
