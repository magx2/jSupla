package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Event;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Location;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.LocationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sc.ServerClientSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.EventSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerClientSerializerFactoryImpl implements ServerClientSerializerFactory {
    private final ChannelPackSerializer channelPackSerializer;
    private final ChannelSerializer channelSerializer;
    private final ChannelValueSerializer channelValueSerializer;
    private final EventSerializer eventSerializer;
    private final LocationPackSerializer locationPackSerializer;
    private final LocationSerializer locationSerializer;
    private final RegisterClientResultSerializer registerClientResultSerializer;

    public ServerClientSerializerFactoryImpl(final ChannelPackSerializer channelPackSerializer,
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
    public Serializer<? extends ServerClientEntity,
                             ? extends ServerClient> getSerializer(@NotNull final ServerClientEntity entity) {
        if (entity instanceof Channel) {
            return channelSerializer;
        } else if (entity instanceof ChannelPack) {
            return channelPackSerializer;
        } else if (entity instanceof ChannelValue) {
            return channelValueSerializer;
        } else if (entity instanceof Event) {
            return eventSerializer;
        } else if (entity instanceof Location) {
            return locationSerializer;
        } else if (entity instanceof LocationPack) {
            return locationPackSerializer;
        } else if (entity instanceof RegisterClientResult) {
            return registerClientResultSerializer;
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
