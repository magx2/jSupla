package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ChannelValueParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.StringParserImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class RegisterClientParserImpl implements RegisterClientParser {
    public static final RegisterClientParserImpl INSTANCE = new RegisterClientParserImpl(StringParserImpl.INSTANCE);
    private final StringParser stringParser;

    RegisterClientParserImpl(final StringParser stringParser) {
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
