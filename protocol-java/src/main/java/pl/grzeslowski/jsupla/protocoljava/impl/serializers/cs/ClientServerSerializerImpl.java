package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ClientServerSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ClientServerSerializerImpl
        implements ClientServerSerializer<ClientServerEntity, ClientServer> {
    private final ChannelNewValueSerializerImpl channelNewValueSerializer;
    private final ChannelNewValueBSerializerImpl channelNewValueBSerializer;
    private final RegisterClientSerializerImpl registerClientSerializer;
    private final RegisterClientBSerializerImpl registerClientBSerializer;

    public ClientServerSerializerImpl(final ChannelNewValueSerializerImpl channelNewValueSerializer,
                                      final ChannelNewValueBSerializerImpl channelNewValueBSerializer,
                                      final RegisterClientSerializerImpl registerClientSerializer,
                                      final RegisterClientBSerializerImpl registerClientBSerializer) {
        this.channelNewValueSerializer = requireNonNull(channelNewValueSerializer);
        this.channelNewValueBSerializer = requireNonNull(channelNewValueBSerializer);
        this.registerClientSerializer = requireNonNull(registerClientSerializer);
        this.registerClientBSerializer = requireNonNull(registerClientBSerializer);
    }

    @Override
    public ClientServer serialize(@NotNull final ClientServerEntity entity) {
        if (entity instanceof ChannelNewValueB) {
            return channelNewValueBSerializer.serialize((ChannelNewValueB) entity);
        } else if (entity instanceof RegisterClientB) {
            return registerClientBSerializer.serialize((RegisterClientB) entity);
        } else if (entity instanceof RegisterClient) {
            return registerClientSerializer.serialize((RegisterClient) entity);
        } else if (entity instanceof ChannelNewValue) {
            return channelNewValueSerializer.serialize((ChannelNewValue) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
