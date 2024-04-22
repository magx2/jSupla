package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelBSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelBSerializerImpl implements ChannelBSerializer {
    private final ChannelValueSerializer channelValueSerializer;
    private final StringSerializer stringSerializer;

    public ChannelBSerializerImpl(ChannelValueSerializer channelValueSerializer, StringSerializer stringSerializer) {
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaChannelB serialize(@NotNull ChannelB entity) {
        return new SuplaChannelB(
            (byte) entity.getEol(),
            entity.getId(),
            entity.getLocationId(),
            entity.getFunction(),
            entity.getAltIcon(),
            entity.getFlags(),
            (short) entity.getProtocolVersion(),
            (byte) (entity.isOnline() ? 1 : 0),
            channelValueSerializer.serialize(entity.getChannelValue()),
            entity.getCaption().length(),
            stringSerializer.serialize(entity.getCaption())
        );
    }
}
