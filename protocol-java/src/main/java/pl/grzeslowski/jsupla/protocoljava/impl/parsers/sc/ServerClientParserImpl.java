package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.*;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.*;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerClientParserImpl implements ServerClientParser<ServerClientEntity, ServerClient> {
    private final LocationParser locationParser;
    private final ChannelPackParser channelPackParser;
    private final EventParser eventParser;
    private final ChannelValueParser channelValueParser;
    private final ChannelParser channelParser;
    private final LocationPackParser locationPackParser;
    private final RegisterClientResultParser registerClientResultParser;
    private final ChannelGroupRelationParser channelGroupRelationParser;
    private final RegisterClientResultBParser registerClientResultBParser;

    public ServerClientParserImpl(final LocationParser locationParser,
                                  final ChannelPackParser channelPackParser,
                                  final EventParser eventParser,
                                  final ChannelValueParser channelValueParser,
                                  final ChannelParser channelParser,
                                  final LocationPackParser locationPackParser,
                                  final RegisterClientResultParser registerClientResultParser,
                                  final ChannelGroupRelationParser channelGroupRelationParser, RegisterClientResultBParser registerClientResultBParser) {
        this.locationParser = requireNonNull(locationParser);
        this.channelPackParser = requireNonNull(channelPackParser);
        this.eventParser = requireNonNull(eventParser);
        this.channelValueParser = requireNonNull(channelValueParser);
        this.channelParser = requireNonNull(channelParser);
        this.locationPackParser = requireNonNull(locationPackParser);
        this.registerClientResultParser = requireNonNull(registerClientResultParser);
        this.channelGroupRelationParser = channelGroupRelationParser;
        this.registerClientResultBParser = registerClientResultBParser;
    }

    @Override
    public ServerClientEntity parse(@NotNull final ServerClient proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaChannel) {
            return channelParser.parse((SuplaChannel) proto);
        } else if (proto instanceof SuplaChannelPack) {
            return channelPackParser.parse((SuplaChannelPack) proto);
        } else if (proto instanceof SuplaChannelValue) {
            return channelValueParser.parse((SuplaChannelValue) proto);
        } else if (proto instanceof SuplaEvent) {
            return eventParser.parse((SuplaEvent) proto);
        } else if (proto instanceof SuplaLocation) {
            return locationParser.parse((SuplaLocation) proto);
        } else if (proto instanceof SuplaLocationPack) {
            return locationPackParser.parse((SuplaLocationPack) proto);
        } else if (proto instanceof SuplaRegisterClientResult) {
            return registerClientResultParser.parse((SuplaRegisterClientResult) proto);
        } else if (proto instanceof SuplaChannelGroupRelation) {
            return channelGroupRelationParser.parse((SuplaChannelGroupRelation) proto);
        } else if (proto instanceof SuplaRegisterClientResultB) {
            return registerClientResultBParser.parse((SuplaRegisterClientResultB) proto);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to parser! %s",
                proto.getClass(), proto));
    }
}
