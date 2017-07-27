package pl.grzeslowski.jsupla.proto.codecs;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.proto.decoders.Decoder;
import pl.grzeslowski.jsupla.proto.encoders.Encoder;

import static java.util.Objects.requireNonNull;

public final class DelegatingCodec<T extends Proto> implements Codec<T> {
    private final Encoder<T> encoder;
    private final Decoder<T> decoder;

    public DelegatingCodec(Encoder<T> encoder, Decoder<T> decoder) {
        this.encoder = requireNonNull(encoder);
        this.decoder = requireNonNull(decoder);
    }

    @Override
    public T decode(byte[] bytes, int offset) {
        return decoder.decode(bytes, offset);
    }

    @Override
    public byte[] encode(T proto) {
        return encoder.encode(proto);
    }
}
