package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface Encoder<T extends ProtoWithSize> {
    byte[] encode(T proto);
}
