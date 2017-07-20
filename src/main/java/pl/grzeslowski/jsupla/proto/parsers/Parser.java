package pl.grzeslowski.jsupla.proto.parsers;

import pl.grzeslowski.jsupla.proto.Proto;

public interface Parser<T extends Proto> {
    T parse(byte[] bytes);
}
