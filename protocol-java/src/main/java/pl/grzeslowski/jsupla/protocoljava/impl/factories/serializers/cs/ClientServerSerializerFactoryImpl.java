package pl.grzeslowski.jsupla.protocoljava.impl.factories.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.cs.ClientServerSerializerFactory;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.RegisterClientBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.RegisterClientSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ClientServerSerializerFactoryImpl implements ClientServerSerializerFactory {
    private final ChannelNewValueSerializerImpl channelNewValueSerializer;
    private final ChannelNewValueBSerializerImpl channelNewValueBSerializer;
    private final RegisterClientSerializerImpl registerClientSerializer;
    private final RegisterClientBSerializerImpl registerClientBSerializer;

    public ClientServerSerializerFactoryImpl(final ChannelNewValueSerializerImpl channelNewValueSerializer,
                                             final ChannelNewValueBSerializerImpl channelNewValueBSerializer,
                                             final RegisterClientSerializerImpl registerClientSerializer,
                                             final RegisterClientBSerializerImpl registerClientBSerializer) {
        this.channelNewValueSerializer = requireNonNull(channelNewValueSerializer);
        this.channelNewValueBSerializer = requireNonNull(channelNewValueBSerializer);
        this.registerClientSerializer = requireNonNull(registerClientSerializer);
        this.registerClientBSerializer = requireNonNull(registerClientBSerializer);
    }

    @Override
    public Serializer<? extends ClientServerEntity, ? extends ClientServer> getSerializer(@NotNull final
                                                                                          ClientServerEntity entity) {
        if (entity instanceof ChannelNewValueB) {
            return channelNewValueBSerializer;
        } else if (entity instanceof RegisterClientB) {
            return registerClientBSerializer;
        } else if (entity instanceof RegisterClient) {
            return registerClientSerializer;
        } else if (entity instanceof ChannelNewValue) {
            return channelNewValueSerializer;
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
