package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.GetVersionResultParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class GetVersionResultParserImpl implements GetVersionResultParser {
    private final StringParser stringParser;

    public GetVersionResultParserImpl(final StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public GetVersionResult parse(@NotNull final SuplaGetVersionResult proto) {
        return new GetVersionResult(
            proto.protoVersionMin,
            proto.protoVersion,
            stringParser.parse(proto.softVer)
        );
    }
}
