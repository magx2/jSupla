package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValuePackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelValuePackSerializerImpl implements ChannelValuePackSerializer {
    private final ChannelValueSerializer channelValueSerializer;

    public ChannelValuePackSerializerImpl(ChannelValueSerializer channelValueSerializer) {
        this.channelValueSerializer = requireNonNull(channelValueSerializer);
    }

    @Override
    public SuplaChannelValuePack serialize(@NotNull ChannelValuePack entity) {
        SuplaChannelValue[] items = entity.getItems()
                .stream()
                .map(channelValueSerializer::serialize)
                .toArray(SuplaChannelValue[]::new);
        return new SuplaChannelValuePack(
                entity.getItems().size(),
                entity.getTotalLeft(),
                items
        );
    }
}
