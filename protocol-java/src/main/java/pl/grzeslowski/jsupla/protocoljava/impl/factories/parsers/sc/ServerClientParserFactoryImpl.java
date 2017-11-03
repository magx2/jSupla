package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sc.ServerClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.EventParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.RegisterClientResultParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerClientParserFactoryImpl implements ServerClientParserFactory {
    private final LocationParser locationParser;
    private final ChannelPackParser channelPackParser;
    private final EventParser eventParser;
    private final ChannelValueParser channelValueParser;
    private final ChannelParser channelParser;
    private final LocationPackParser locationPackParser;
    private final RegisterClientResultParser registerClientResultParser;

    public ServerClientParserFactoryImpl(final LocationParser locationParser,
                                         final ChannelPackParser channelPackParser,
                                         final EventParser eventParser,
                                         final ChannelValueParser channelValueParser,
                                         final ChannelParser channelParser,
                                         final LocationPackParser locationPackParser,
                                         final RegisterClientResultParser registerClientResultParser) {
        this.locationParser = requireNonNull(locationParser);
        this.channelPackParser = requireNonNull(channelPackParser);
        this.eventParser = requireNonNull(eventParser);
        this.channelValueParser = requireNonNull(channelValueParser);
        this.channelParser = requireNonNull(channelParser);
        this.locationPackParser = requireNonNull(locationPackParser);
        this.registerClientResultParser = requireNonNull(registerClientResultParser);
    }

    @Override
    public Parser<? extends ServerClientEntity, ? extends ServerClient> getParser(@NotNull final ServerClient proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaChannel) {
            return channelParser;
        } else if (proto instanceof SuplaChannelPack) {
            return channelPackParser;
        } else if (proto instanceof SuplaChannelValue) {
            return channelValueParser;
        } else if (proto instanceof SuplaEvent) {
            return eventParser;
        } else if (proto instanceof SuplaLocation) {
            return locationParser;
        } else if (proto instanceof SuplaLocationPack) {
            return locationPackParser;
        } else if (proto instanceof SuplaRegisterClientResult) {
            return registerClientResultParser;
        }
        throw new IllegalArgumentException(format("Don't know this type of proto. Class name: %s.",
                proto.getClass().getSimpleName()));
    }
}
