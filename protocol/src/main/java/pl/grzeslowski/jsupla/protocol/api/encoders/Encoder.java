package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface Encoder<T extends ProtoWithSize> {
    default byte[] encode(T proto) {
        return encode(proto, new byte[proto.protoSize()], 0);
    }

    byte[] encode(T proto, byte[] bytes, int offset);
}
