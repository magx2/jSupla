package pl.grzeslowski.jsupla.proto.decoders;

import pl.grzeslowski.jsupla.proto.Proto;

public interface Decoder<T extends Proto> {
    default T parse(byte[] bytes) {
        return parse(bytes, 0);
    }

    T parse(byte[] bytes, int offset);
}
