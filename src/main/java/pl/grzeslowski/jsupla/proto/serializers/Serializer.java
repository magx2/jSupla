package pl.grzeslowski.jsupla.proto.serializers;

import pl.grzeslowski.jsupla.proto.Proto;

public interface Serializer<T extends Proto> {
    byte[] serialise(T proto);
}
