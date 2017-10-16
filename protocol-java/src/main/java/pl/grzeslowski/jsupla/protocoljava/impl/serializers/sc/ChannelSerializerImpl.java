package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelSerializerImpl implements ChannelSerializer {
    private final ChannelValueSerializer channelValueSerializer;
    private final StringSerializer stringSerializer;

    public ChannelSerializerImpl(final ChannelValueSerializer channelValueSerializer, final StringSerializer stringSerializer) {
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaChannel serialize(@NotNull final Channel entity) {
        return new SuplaChannel(
                                       (byte) entity.getEol(),
                                       entity.getId(),
                                       entity.getLocationId(),
                                       entity.getFunction(),
                                       (byte) (entity.isOnline() ? 1 : 0),
                                       channelValueSerializer.serialize(entity.getChannelValue()),
                                       entity.getCaption().length(),
                                       stringSerializer.serialize(entity.getCaption())
        );
    }
}
