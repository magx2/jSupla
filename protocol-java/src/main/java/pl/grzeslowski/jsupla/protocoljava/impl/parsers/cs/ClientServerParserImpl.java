package pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ClientServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ClientServerParserImpl implements ClientServerEntityParser<ClientServerEntity, ClientServer> {
    private final ChannelNewValueBParser channelNewValueBParser;
    private final ChannelNewValueParser channelNewValueParser;
    private final RegisterClientBParser registerClientBParser;
    private final RegisterClientParser registerClientParser;

    public ClientServerParserImpl(final ChannelNewValueBParser channelNewValueBParser,
                                  final ChannelNewValueParser channelNewValueParser,
                                  final RegisterClientBParser registerClientBParser,
                                  final RegisterClientParser registerClientParser) {
        this.channelNewValueBParser = requireNonNull(channelNewValueBParser);
        this.channelNewValueParser = requireNonNull(channelNewValueParser);
        this.registerClientBParser = requireNonNull(registerClientBParser);
        this.registerClientParser = requireNonNull(registerClientParser);
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
        }
        throw new IllegalArgumentException(format("Don't know this type of proto. Class name: %s.",
                proto.getClass().getSimpleName()));
    }
}
