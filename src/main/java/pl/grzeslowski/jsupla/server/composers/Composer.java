package pl.grzeslowski.jsupla.server.composers;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.server.entities.Entity;
import pl.grzeslowski.jsupla.server.parsers.Parser;
import pl.grzeslowski.jsupla.server.serializers.Serializer;

public interface Composer<E extends Entity, P extends Proto> extends Parser<E, P>, Serializer<E, P> {
}
