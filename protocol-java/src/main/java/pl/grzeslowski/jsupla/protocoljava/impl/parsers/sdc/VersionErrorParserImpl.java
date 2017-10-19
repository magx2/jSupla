package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.VersionErrorParser;

import javax.validation.constraints.NotNull;

public class VersionErrorParserImpl implements VersionErrorParser {
    @Override
    public VersionError parse(@NotNull final SuplaVersionError proto) {
        return new VersionError(proto.serverVersionMin, proto.serverVersion);
    }
}
