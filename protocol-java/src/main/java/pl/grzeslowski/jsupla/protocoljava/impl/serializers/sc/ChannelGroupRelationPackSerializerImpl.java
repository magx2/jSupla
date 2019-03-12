package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupRelationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupRelationSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelGroupRelationPackSerializerImpl implements ChannelGroupRelationPackSerializer {
    private final ChannelGroupRelationSerializer channelGroupRelationSerializer;

    public ChannelGroupRelationPackSerializerImpl(ChannelGroupRelationSerializer channelGroupRelationSerializer) {
        this.channelGroupRelationSerializer = requireNonNull(channelGroupRelationSerializer);
    }

    @Override
    public SuplaChannelGroupRelationPack serialize(@NotNull ChannelGroupRelationPack entity) {
        SuplaChannelGroupRelation[] items = entity.getItems()
                .stream()
                .map(channelGroupRelationSerializer::serialize)
                .toArray(SuplaChannelGroupRelation[]::new);
        return new SuplaChannelGroupRelationPack(
                entity.getItems().size(),
                entity.getTotalLeft(),
                items
        );
    }
}
