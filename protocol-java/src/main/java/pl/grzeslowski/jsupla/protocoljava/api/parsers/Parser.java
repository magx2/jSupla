package pl.grzeslowski.jsupla.protocoljava.api.parsers;

import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

public interface Parser<EntityT extends Entity, SuplaProtoT extends Proto> {
    @NotNull
    EntityT parse(@NotNull SuplaProtoT proto);
}
