package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface Encoder<T extends ProtoWithSize> {
    default byte[] encode(T proto) {
        var bytes = new byte[proto.protoSize()];
        encode(proto, bytes, 0);
        return bytes;
    }

    int encode(T proto, byte[] bytes, int offset);
}
