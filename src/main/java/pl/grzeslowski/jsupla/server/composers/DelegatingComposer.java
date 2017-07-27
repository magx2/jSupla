package pl.grzeslowski.jsupla.server.composers;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.server.entities.Entity;
import pl.grzeslowski.jsupla.server.parsers.Parser;
import pl.grzeslowski.jsupla.server.serializers.Serializer;

import static java.util.Objects.requireNonNull;

public final class DelegatingComposer<E extends Entity, P extends Proto> implements Composer<E, P> {
    private final Parser<E, P> parser;
    private final Serializer<E, P> serializer;

    public DelegatingComposer(Parser<E, P> parser, Serializer<E, P> serializer) {
        this.parser = requireNonNull(parser);
        this.serializer = requireNonNull(serializer);
    }

    @Override
    public E parse(P proto) {
        return parser.parse(proto);
    }

    @Override
    public P serialize(E entity) {
        return serializer.serialize(entity);
    }
}
