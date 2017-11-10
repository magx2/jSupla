package pl.grzeslowski.jsupla.protocoljava.api.factories.serializers;

import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

public interface SerializerFactory<EntityT extends Entity, SuplaProtoT extends Proto> {
    @NotNull Serializer<? extends EntityT, ? extends SuplaProtoT> getSerializer(@NotNull EntityT entity);
}
