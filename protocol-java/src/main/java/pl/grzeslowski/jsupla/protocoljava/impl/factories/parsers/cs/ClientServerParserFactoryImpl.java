package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.cs.ClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ClientServerParserFactoryImpl implements ClientServerParserFactory {
    private final ChannelNewValueBParser channelNewValueBParser;
    private final ChannelNewValueParser channelNewValueParser;
    private final RegisterClientBParser registerClientBParser;
    private final RegisterClientParser registerClientParser;

    public ClientServerParserFactoryImpl(final ChannelNewValueBParser channelNewValueBParser,
                                         final ChannelNewValueParser channelNewValueParser,
                                         final RegisterClientBParser registerClientBParser,
                                         final RegisterClientParser registerClientParser) {
        this.channelNewValueBParser = requireNonNull(channelNewValueBParser);
        this.channelNewValueParser = requireNonNull(channelNewValueParser);
        this.registerClientBParser = requireNonNull(registerClientBParser);
        this.registerClientParser = requireNonNull(registerClientParser);
    }

    @Override
    public Parser<? extends ClientServerEntity, ? extends ClientServer> getParser(@NotNull final ClientServer proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaChannelNewValueB) {
            return channelNewValueBParser;
        } else if (proto instanceof SuplaChannelNewValue) {
            return channelNewValueParser;
        } else if (proto instanceof SuplaRegisterClient) {
            return registerClientParser;
        } else if (proto instanceof SuplaRegisterClientB) {
            return registerClientBParser;
        }
        throw new IllegalArgumentException(format("Don't know this type of proto. Class name: %s.",
                proto.getClass().getSimpleName()));
    }
}
