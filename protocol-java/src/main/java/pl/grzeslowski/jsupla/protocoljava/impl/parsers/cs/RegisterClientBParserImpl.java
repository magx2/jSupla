package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientBParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class RegisterClientBParserImpl implements RegisterClientBParser {
    private final StringParser stringParser;

    public RegisterClientBParserImpl(final StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public RegisterClientB parse(@NotNull final SuplaRegisterClientB proto) {
        return new RegisterClientB(
            proto.accessId,
            stringParser.parsePassword(proto.accessIdPwd),
            stringParser.parse(proto.guid),
            stringParser.parse(proto.name),
            stringParser.parse(proto.softVer),
            stringParser.parse(proto.serverName)
        );
    }
}
