package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelValueSerializerImpl implements ChannelValueSerializer {
    private final pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer channelValueSerializer;

    public ChannelValueSerializerImpl(
                                             final pl.grzeslowski.jsupla.protocoljava.api.serializers
                                                           .ChannelValueSerializer channelValueSerializer) {
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
    }

    @Override
    public SuplaChannelValue serialize(@NotNull final ChannelValue entity) {
        return new SuplaChannelValue(
                                            (byte) entity.getEol(),
                                            entity.getId(),
                                            (byte) (entity.isOnline() ? 1 : 0),
                                            channelValueSerializer.serialize(entity.getValue())
        );
    }
}
