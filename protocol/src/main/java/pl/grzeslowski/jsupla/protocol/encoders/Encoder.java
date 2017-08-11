package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.types.ProtoWithSize;

public interface Encoder<T extends ProtoWithSize> {
    byte[] encode(T proto);
}
