package pl.grzeslowski.jsupla.protocoljava.api.factories.parsers;

import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.NotNull;

public interface ParserFactory<EntityT extends Entity, SuplaProtoT extends Proto> {
    @NotNull Parser<? extends EntityT, ? extends SuplaProtoT> getParser(@NotNull SuplaProtoT proto);
}
