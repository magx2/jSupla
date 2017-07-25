package pl.grzeslowski.jsupla.proto.parsers;

import pl.grzeslowski.jsupla.proto.Proto;

public interface Parser<T extends Proto> {
    default T parse(byte[] bytes) {
        return parse(bytes, 0);
    }

    T parse(byte[] bytes, int offset);
}
