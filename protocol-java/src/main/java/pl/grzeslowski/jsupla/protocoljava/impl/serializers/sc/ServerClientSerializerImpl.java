package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.EventSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ServerClientSerializer;

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

    public ServerClientSerializerImpl(final ChannelPackSerializer channelPackSerializer,
                                      final ChannelSerializer channelSerializer,
                                      final ChannelValueSerializer channelValueSerializer,
                                      final EventSerializer eventSerializer,
                                      final LocationPackSerializer locationPackSerializer,
                                      final LocationSerializer locationSerializer,
                                      final RegisterClientResultSerializer registerClientResultSerializer) {
        this.channelPackSerializer = requireNonNull(channelPackSerializer);
        this.channelSerializer = requireNonNull(channelSerializer);
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
        this.eventSerializer = requireNonNull(eventSerializer);
        this.locationPackSerializer = requireNonNull(locationPackSerializer);
        this.locationSerializer = requireNonNull(locationSerializer);
        this.registerClientResultSerializer = requireNonNull(registerClientResultSerializer);
    }

    @Override
    public ServerClient serialize(@NotNull final ServerClientEntity entity) {
        if (entity instanceof Channel) {
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
        } else if (entity instanceof RegisterClientResult) {
            return registerClientResultSerializer.serialize((RegisterClientResult) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
