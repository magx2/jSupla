package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class RegisterClientParserImpl implements RegisterClientParser {
    private final StringParser stringParser;

    public RegisterClientParserImpl(final StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public RegisterClient parse(@NotNull final SuplaRegisterClient proto) {
        return new RegisterClient(
            proto.accessId,
            stringParser.parsePassword(proto.accessIdPwd),
            stringParser.parse(proto.guid),
            stringParser.parse(proto.name),
            stringParser.parse(proto.softVer)
        );
    }
}
