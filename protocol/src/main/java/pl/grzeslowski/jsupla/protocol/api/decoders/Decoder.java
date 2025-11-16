package pl.grzeslowski.jsupla.protocol.api.decoders;

public interface Decoder<T> {
    T decode(byte[] bytes, int offset);

    default T decode(byte[] bytes) {
        return decode(bytes, 0);
    }
}
