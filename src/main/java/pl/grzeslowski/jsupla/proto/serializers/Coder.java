package pl.grzeslowski.jsupla.proto.serializers;

import pl.grzeslowski.jsupla.proto.Proto;

public interface Coder<T extends Proto> {
    byte[] serialise(T proto);
}
