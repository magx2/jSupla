package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.*;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.*;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ClientServerParserImpl implements ClientServerParser<ClientServerEntity, ClientServer> {
    private final ChannelNewValueBParser channelNewValueBParser;
    private final ChannelNewValueParser channelNewValueParser;
    private final RegisterClientBParser registerClientBParser;
    private final RegisterClientParser registerClientParser;
    private final RegisterClientCParser registerClientCParser;
    private final NewValueParser newValueParser;

    public ClientServerParserImpl(final ChannelNewValueBParser channelNewValueBParser,
                                  final ChannelNewValueParser channelNewValueParser,
                                  final RegisterClientBParser registerClientBParser,
                                  final RegisterClientParser registerClientParser,
                                  final RegisterClientCParser registerClientCParser,
                                  final NewValueParser newValueParser) {
        this.channelNewValueBParser = requireNonNull(channelNewValueBParser);
        this.channelNewValueParser = requireNonNull(channelNewValueParser);
        this.registerClientBParser = requireNonNull(registerClientBParser);
        this.registerClientParser = requireNonNull(registerClientParser);
        this.registerClientCParser = requireNonNull(registerClientCParser);
        this.newValueParser = requireNonNull(newValueParser);
    }

    @Override
    public ClientServerEntity parse(@NotNull final ClientServer proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaChannelNewValueB) {
            return channelNewValueBParser.parse((SuplaChannelNewValueB) proto);
        } else if (proto instanceof SuplaChannelNewValue) {
            return channelNewValueParser.parse((SuplaChannelNewValue) proto);
        } else if (proto instanceof SuplaRegisterClient) {
            return registerClientParser.parse((SuplaRegisterClient) proto);
        } else if (proto instanceof SuplaRegisterClientB) {
            return registerClientBParser.parse((SuplaRegisterClientB) proto);
        } else if (proto instanceof SuplaRegisterClientC) {
            return registerClientCParser.parse((SuplaRegisterClientC) proto);
        } else if (proto instanceof SuplaNewValue) {
            return newValueParser.parse((SuplaNewValue) proto);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to parser! %s",
                proto.getClass(), proto));
    }
}
