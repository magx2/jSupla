package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.*;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerClientSerializerImpl
        implements ServerClientSerializer<ServerClientEntity, ServerClient> {
    private final ChannelPackSerializer channelPackSerializer;
    private final ChannelSerializer channelSerializer;
    private final ChannelValueSerializer channelValueSerializer;
    private final EventSerializer eventSerializer;
    private final LocationPackSerializer locationPackSerializer;
    private final LocationSerializer locationSerializer;
    private final RegisterClientResultSerializer registerClientResultSerializer;
    private final ChannelGroupRelationSerializer channelGroupRelationSerializer;
    private final RegisterClientResultBSerializer registerClientResultBSerializer;
    private final ChannelGroupRelationPackSerializer channelGroupRelationPackSerializer;
    private final ChannelBSerializer channelBSerializer;

    public ServerClientSerializerImpl(final ChannelPackSerializer channelPackSerializer,
                                      final ChannelSerializer channelSerializer,
                                      final ChannelValueSerializer channelValueSerializer,
                                      final EventSerializer eventSerializer,
                                      final LocationPackSerializer locationPackSerializer,
                                      final LocationSerializer locationSerializer,
                                      final RegisterClientResultSerializer registerClientResultSerializer,
                                      final ChannelGroupRelationSerializer channelGroupRelationSerializer,
                                      final RegisterClientResultBSerializer registerClientResultBSerializer,
                                      final ChannelGroupRelationPackSerializer channelGroupRelationPackSerializer,
                                      final ChannelBSerializer channelBSerializer) {
        this.channelPackSerializer = requireNonNull(channelPackSerializer);
        this.channelSerializer = requireNonNull(channelSerializer);
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
        this.eventSerializer = requireNonNull(eventSerializer);
        this.locationPackSerializer = requireNonNull(locationPackSerializer);
        this.locationSerializer = requireNonNull(locationSerializer);
        this.registerClientResultSerializer = requireNonNull(registerClientResultSerializer);
        this.channelGroupRelationSerializer = requireNonNull(channelGroupRelationSerializer);
        this.registerClientResultBSerializer = requireNonNull(registerClientResultBSerializer);
        this.channelGroupRelationPackSerializer = requireNonNull(channelGroupRelationPackSerializer);
        this.channelBSerializer = requireNonNull(channelBSerializer);
    }

    @Override
    public ServerClient serialize(@NotNull final ServerClientEntity entity) {
        if (entity instanceof ChannelB) {
            return channelBSerializer.serialize((ChannelB) entity);
        } else if (entity instanceof Channel) {
            return channelSerializer.serialize((Channel) entity);
        } else if (entity instanceof ChannelPack) {
            return channelPackSerializer.serialize((ChannelPack) entity);
        } else if (entity instanceof ChannelValue) {
            return channelValueSerializer.serialize((ChannelValue) entity);
        } else if (entity instanceof Event) {
            return eventSerializer.serialize((Event) entity);
        } else if (entity instanceof Location) {
            return locationSerializer.serialize((Location) entity);
        } else if (entity instanceof LocationPack) {
            return locationPackSerializer.serialize((LocationPack) entity);
        } else if (entity instanceof RegisterClientResultB) {
            return registerClientResultBSerializer.serialize((RegisterClientResultB) entity);
        } else if (entity instanceof RegisterClientResult) {
            return registerClientResultSerializer.serialize((RegisterClientResult) entity);
        } else if (entity instanceof ChannelGroupRelation) {
            return channelGroupRelationSerializer.serialize((ChannelGroupRelation) entity);
        } else if (entity instanceof ChannelGroupRelationPack) {
            return channelGroupRelationPackSerializer.serialize((ChannelGroupRelationPack) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
