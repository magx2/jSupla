package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.server.entities.Entity;

public interface Parser<E extends Entity, P extends Proto> {
    E parse(P proto);
}
