package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface Encoder<T extends ProtoWithSize> {
    byte[] encode(T proto);

    /* todo in the future use this methods
    default byte[] encode(T proto) {
        return encode(proto, new byte[proto.size()]);
    }

    byte[] encode(T proto, byte[] bytes, int offset);
     */
}
