package pl.grzeslowski.jsupla.protocol.api.serialization;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface PayloadCodec<T extends ProtoWithSize> {
    byte[] encode(T payload);

    T decode(byte[] bytes);
}
