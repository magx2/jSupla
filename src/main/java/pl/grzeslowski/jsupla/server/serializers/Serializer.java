package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.server.entities.Entity;

public interface Serializer<E extends Entity, P extends Proto> {
    P serialize(E entity);
}
