package pl.grzeslowski.jsupla.proto.codecs;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.proto.decoders.Decoder;
import pl.grzeslowski.jsupla.proto.encoders.Encoder;

public interface Codec<T extends Proto> extends Encoder<T>, Decoder<T> {
}
