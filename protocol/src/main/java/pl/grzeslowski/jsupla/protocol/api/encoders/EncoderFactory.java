package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface EncoderFactory {
    <T extends ProtoWithSize> Encoder<T> getEncoder(Class<T> proto);

    @SuppressWarnings("unchecked")
    default <T extends ProtoWithSize> Encoder<T> getEncoder(T proto) {
        return (Encoder<T>) getEncoder(proto.getClass());
    }
}
