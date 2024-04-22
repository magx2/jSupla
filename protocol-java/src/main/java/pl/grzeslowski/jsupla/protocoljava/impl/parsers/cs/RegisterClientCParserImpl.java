package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientC;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientCParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class RegisterClientCParserImpl implements RegisterClientCParser {
    private final StringParser stringParser;

    public RegisterClientCParserImpl(StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public RegisterClientC parse(@NotNull SuplaRegisterClientC proto) {
        return new RegisterClientC(
            stringParser.parse(proto.email),
            stringParser.parse(proto.authKey),
            stringParser.parse(proto.guid),
            stringParser.parse(proto.name),
            stringParser.parse(proto.softVer),
            stringParser.parse(proto.serverName)
        );
    }
}
