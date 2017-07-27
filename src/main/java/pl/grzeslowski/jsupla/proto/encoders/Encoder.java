package pl.grzeslowski.jsupla.proto.encoders;

import pl.grzeslowski.jsupla.proto.Proto;

public interface Encoder<T extends Proto> {
    void encode(T proto, byte[] bytes, int offset);
}
