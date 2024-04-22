package pl.grzeslowski.jsupla.protocoljava.api.serializers;

import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

public interface Serializer<EntityT extends Entity, SuplaProtoT extends Proto> {
    @NotNull
    SuplaProtoT serialize(@NotNull EntityT entity);
}
