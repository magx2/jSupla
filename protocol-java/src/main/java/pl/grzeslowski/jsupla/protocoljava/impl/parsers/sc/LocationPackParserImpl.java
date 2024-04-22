package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationParser;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class LocationPackParserImpl implements LocationPackParser {
    private final LocationParser locationParser;

    public LocationPackParserImpl(final LocationParser locationParser) {
        this.locationParser = requireNonNull(locationParser);
    }

    @Override
    public LocationPack parse(@NotNull final SuplaLocationPack proto) {
        return new LocationPack(
            proto.totalLeft,
            Arrays.stream(proto.locations)
                .map(locationParser::parse)
                .collect(toList())
        );
    }
}
