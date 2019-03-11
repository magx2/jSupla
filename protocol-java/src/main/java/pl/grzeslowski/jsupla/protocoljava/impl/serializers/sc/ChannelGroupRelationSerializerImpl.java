package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupRelationSerializer;

import javax.validation.constraints.NotNull;

public class ChannelGroupRelationSerializerImpl implements ChannelGroupRelationSerializer {
    @Override
    public SuplaChannelGroupRelation serialize(@NotNull ChannelGroupRelation entity) {
        return new SuplaChannelGroupRelation(
                (byte) entity.getEol(),
                entity.getChannelGroupId(),
                entity.getChannelId()
        );
    }
}
