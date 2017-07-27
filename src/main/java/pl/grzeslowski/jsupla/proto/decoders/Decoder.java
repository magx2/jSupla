package pl.grzeslowski.jsupla.proto.decoders;

import pl.grzeslowski.jsupla.proto.Proto;

public interface Decoder<T extends Proto> {
    default T decode(byte[] bytes) {
        return decode(bytes, 0);
    }

    T decode(byte[] bytes, int offset);
}
